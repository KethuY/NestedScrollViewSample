package com.kethu.nestedscrollviewsample

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.QuickContactBadge
import android.widget.TextView
import org.json.JSONObject


class MainActivity : AppCompatActivity(),ListenerCh {
    override fun getJSONObj(jsonObject: JSONObject?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bestRecyclerView = findViewById<View>(R.id.product_list) as RecyclerView
        var q=findViewById<QuickContactBadge>(R.id.quickContactBadge) as QuickContactBadge
        q.setImageResource(R.mipmap.ic_launcher)

        val mGrid = GridLayoutManager(this, 2)
        bestRecyclerView.layoutManager = mGrid
        bestRecyclerView.setHasFixedSize(true)
        bestRecyclerView.isNestedScrollingEnabled = false
        val mAdapter = ProductAdapter(this@MainActivity, getProductTestData())
        bestRecyclerView.adapter = mAdapter


    }



    private fun getProductTestData(): List<ProductObject> {
        val featuredProducts = ArrayList<ProductObject>()
        featuredProducts.add(ProductObject("Gig Laptop", "lap1"))
        featuredProducts.add(ProductObject("Mash Laptop", "lap1"))
        featuredProducts.add(ProductObject("Blue Dot Lapto", "lap1"))
        featuredProducts.add(ProductObject("Skin Laptop", "lap1"))
        featuredProducts.add(ProductObject("Dice Laptop", "lap1"))
        featuredProducts.add(ProductObject("Ingrate Laptop", "lap1"))
        featuredProducts.add(ProductObject("Mush Laptop", "lap1"))
        featuredProducts.add(ProductObject("Stained Laptop", "lap1"))
        return featuredProducts
    }

    inner class ProductObject(val name: String, val imagePath: String)

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage: ImageView
        var productName: TextView

        init {
            productImage = itemView.findViewById<View>(R.id.product_image) as ImageView
            productName = itemView.findViewById<View>(R.id.product_name) as TextView
        }
    }

    inner class ProductAdapter(private val context: Context, private val productList: List<ProductObject>) : RecyclerView.Adapter<ProductViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
            return ProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val productObject = productList[position]
            val imageRes = getResourceId(context, productObject.imagePath, "drawable", context.getPackageName())
            holder.productImage.setImageResource(imageRes)
            holder.productName.text = productObject.name
        }

        override fun getItemCount(): Int {
            return productList.size
        }

        @Throws(RuntimeException::class)
        fun getResourceId(context: Context, pVariableName: String, pResourcename: String, pPackageName: String): Int {
            try {
                return context.resources.getIdentifier(pVariableName, pResourcename, pPackageName)
            } catch (e: Exception) {
                throw RuntimeException("Error getting Resource ID.", e)
            }

        }
    }
}
