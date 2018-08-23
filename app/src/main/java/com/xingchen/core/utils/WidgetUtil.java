package com.xingchen.core.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 控件处理帮助类
 * 
 */
public class WidgetUtil {
	protected static String TAG = "WidgetUtil";
	/**
	 * Activity显示的宽和高
	 * 
	 * @param activity
	 * @return
	 */
	public static DisplayMetrics getMetric(Activity activity) {
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric;
	}

	/**
	 * 设置listview的高度
	 * 
	 * @param listview
	 */
	public static void setListViewHeightBasedOnChildren(ListView listview) {
		ViewGroup.LayoutParams params = listview.getLayoutParams();
		params.height = getLvHeight(listview);
		listview.setLayoutParams(params);
	}

	/**
	 * 设置带footview的listview的高度
	 * 
	 * @param listview
	 * @param foot
	 */
	public static void setListViewHeightBasedOnFoot(ListView listview, View foot) {
		ViewGroup.LayoutParams params = listview.getLayoutParams();
		params.height = getLvHeight(listview) + foot.getHeight();
		listview.setLayoutParams(params);
	}

	/**
	 * 计算ListView的高度
	 * 
	 * @param listview
	 * @return
	 */
	public static int getLvHeight(ListView listview) {
		int totalHeight = 0;
		ListAdapter adapter = listview.getAdapter();
		int len = adapter.getCount() < 6 ? adapter.getCount() : 5;
		if (null != adapter) {
			for (int i = 0; i < len; i++) { // listAdapter.getCount()返回数据项的数目
				View listItem = adapter.getView(i, null, listview);
				listItem.measure(0, 0); // 计算子项View 的宽高
				totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
			}
			totalHeight += (listview.getDividerHeight() * (len - 1) + listview.getPaddingTop() + listview.getPaddingBottom());
		}
		return totalHeight;
	}

	/** * 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
	public static int dip2px(Context c, float dpValue) {
		final float scale = c.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/** * 根据手机的分辨率从 px(像素) 的单位 转成为 dp */
	public static int px2dip(Context c, float pxValue) {
		final float scale = c.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	
}
