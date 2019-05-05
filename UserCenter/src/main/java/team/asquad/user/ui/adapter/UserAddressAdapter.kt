package team.asquad.user.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.item_usercenter_useraddress.view.*
import team.asquad.base.ext.onClick
import team.asquad.base.ext.toast
import team.asquad.base.ui.adapter.BaseRecyclerViewAdapter
import team.asquad.user.R
import team.asquad.user.data.protocol.UserAddress

/**
 *   @Author ACloud
 *   @Time 2019/4/2 20:45
 *   @Explain
 *   @Version
 **/
class UserAddressAdapter(context: Context):RecyclerView.Adapter<UserAddressAdapter.ViewHolder>() {
    var mContext: Context = context
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        // 填充内容
        val item: View = LayoutInflater.from(mContext).inflate(R.layout.item_usercenter_useraddress, parent, false)

        return UserAddressAdapter.ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mobile: String = "13612374509"
        holder.itemView.mItemPhoneNumberTv.text = mobile
        holder.itemView.mItemUserCenterForNameTv.text = "张三"
        holder.itemView.ItemAddressTv.text = "中华民族共和国 广东省 汕头市 澄海区 隆都镇"
        holder.itemView.mItemEditTv.onClick {
            mContext.toast("编辑").show()
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}