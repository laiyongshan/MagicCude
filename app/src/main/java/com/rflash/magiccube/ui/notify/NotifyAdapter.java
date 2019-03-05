package com.rflash.magiccube.ui.notify;

import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/12 09:46
 * @desc:
 */

public class NotifyAdapter extends BaseQuickAdapter<NotifyBean.ResultBean,BaseViewHolder> {

    public NotifyAdapter( List<NotifyBean.ResultBean> data) {
        super(R.layout.item_notify_rv, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NotifyBean.ResultBean item) {
        ((TextView)helper.getView(R.id.createTime_tv)).setText(""+item.getDate());
        ((TextView)helper.getView(R.id.nontify_title_amtv)).setText(""+item.getTitle());

        RichText.from(item.getContent().replace("width:500px","width:100px")).bind(this)
                .autoFix(true)
                .autoPlay(true) // gif图片是否自动播放
                .showBorder(true) // 是否显示图片边框
                .borderColor(Color.BLACK) // 图片边框颜色
                .borderSize(10) // 边框尺寸
                .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)
                .into(((TextView)helper.getView(R.id.notify_content_tv)));

        ((WebView)helper.getView(R.id.weview)).getSettings().setDefaultTextEncodingName("UTF-8") ;

        //设置webView里字体大小
        WebSettings settings= ((WebView)helper.getView(R.id.weview)).getSettings();
        settings.setSupportZoom(true);
        settings.setTextSize(WebSettings.TextSize.SMALLER);
        settings.setLoadWithOverviewMode(true);
        ((WebView)helper.getView(R.id.weview)).loadData(item.getContent(), "text/html; charset=UTF-8", null) ;
//        ((WebView)helper.getView(R.id.weview)).loadData();
    }

}
