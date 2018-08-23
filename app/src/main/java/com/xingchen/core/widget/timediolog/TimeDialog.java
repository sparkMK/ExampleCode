package com.xingchen.core.widget.timediolog;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xingchen.core.R;
import com.xingchen.core.utils.WidgetUtil;


public class TimeDialog extends Dialog {

	private Context context;
	private Boolean ymd = false;
	private Boolean dm = false;

	private static int START_YEAR = 1990, END_YEAR = 2100;
	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH);
	int day = calendar.get(Calendar.DATE);
	int hour = calendar.get(Calendar.HOUR_OF_DAY);
	int minute = calendar.get(Calendar.MINUTE);
	int theme;
	private OnSetListener mCallBack;
	private OnDismissListener mCallBackDismiss;

	public interface OnSetListener {

		void onDateSet(int year, int monthOfYear, int dayOfMonth);

		void onTimeSet(int hour, int minute);

	}

	public interface OnDismissListener {

		void onDismiss();
	}

	public static abstract class OnDateSetListener implements OnSetListener {

		public abstract void onDateSet(int year, int monthOfYear, int dayOfMonth);

		public void onTimeSet(int hour, int minute) {
		};

	}

	public static abstract class OnTimeSetListener implements OnSetListener {

		public void onDateSet(int year, int monthOfYear, int dayOfMonth) {

		}

		public abstract void onTimeSet(int hour, int minute);

	}

	public TimeDialog(Context context, OnDateSetListener CallBack, int theme) {
		super(context, theme);
	}

	public TimeDialog(Context context, OnSetListener CallBack, int hour, int minute, int theme) {
		super(context, theme);
		this.context = context;
		this.mCallBack = CallBack;
		this.hour = hour;
		this.minute = minute;
		this.theme = theme;
	}

	public TimeDialog(Context context, OnSetListener CallBack, int year, int month, int day, int theme) {
		super(context, theme);
		this.context = context;
		this.mCallBack = CallBack;
		this.year = year;
		this.month = month;
		this.day = day;
		this.theme = theme;
	}

	public TimeDialog(Context context, OnSetListener CallBack, OnDismissListener DismissCallBack, int hour, int minute,
			int theme) {
		super(context, theme);
		this.context = context;
		this.mCallBack = CallBack;
		this.mCallBackDismiss = DismissCallBack;
		this.hour = hour;
		this.minute = minute;
		this.theme = theme;
	}

	public TimeDialog setYmd(Boolean ymd) {
		this.ymd = ymd;
		return this;
	}

	public TimeDialog setDm(Boolean dm) {
		this.dm = dm;
		return this;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
	String[] months_little = { "4", "6", "9", "11" };

	final List<String> list_big = Arrays.asList(months_big);
	final List<String> list_little = Arrays.asList(months_little);

	public TimeDialog createDialog() {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout view = (LinearLayout) inflater.inflate(R.layout.smart_lock_common_timedialog_view, null);
		if (ymd == false && dm == true) {
			view.setGravity(Gravity.CENTER);
			view.setPadding(WidgetUtil.dip2px(context, 12), WidgetUtil.dip2px(context, 12),
					WidgetUtil.dip2px(context, 12), 0);
		}

		final WheelView wv_day = (WheelView) view.findViewById(R.id.smart_lock_timedialog_day);
		final WheelView wv_month = (WheelView) view.findViewById(R.id.smart_lock_timedialog_month);
		final WheelView wv_year = (WheelView) view.findViewById(R.id.smart_lock_timedialog_year);
		final WheelView wv_hours = (WheelView) view.findViewById(R.id.smart_lock_timedialog_hour);
		final WheelView wv_mins = (WheelView) view.findViewById(R.id.smart_lock_timedialog_mins);

		// final TimeDialog dialog = new TimeDialog(context, mCallBack, year,
		// month, day, theme);
		if (ymd == true) {
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
			wv_day.setVisibility(View.VISIBLE);
			wv_month.setVisibility(View.VISIBLE);
			wv_year.setVisibility(View.VISIBLE);
		}
		if (dm == true) {
			wv_hours.setVisibility(View.VISIBLE);
			wv_mins.setVisibility(View.VISIBLE);
			wv_day.setVisibility(View.GONE);
			wv_month.setVisibility(View.GONE);
			wv_year.setVisibility(View.GONE);
		}

		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));
		wv_year.setCyclic(true);
		wv_year.setLabel("");
		wv_year.setCurrentItem(year - START_YEAR);

		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("");
		wv_month.setCurrentItem(month);

		wv_day.setCyclic(true);
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}

		wv_day.setLabel("");
		wv_day.setCurrentItem(day - 1);

		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				if (list_big.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					if (wv_day.getCurrentItem() > 29) {
						wv_day.setCurrentItem(29, isShowing());
					}
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						if (wv_day.getCurrentItem() > 28) {
							wv_day.setCurrentItem(28, isShowing());
						}
					} else {
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						if (wv_day.getCurrentItem() > 27) {
							wv_day.setCurrentItem(27, isShowing());
						}
					}

				}
			}
		};

		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					if (wv_day.getCurrentItem() > 29) {
						wv_day.setCurrentItem(29, isShowing());
					}
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						if (wv_day.getCurrentItem() > 28) {
							wv_day.setCurrentItem(28, isShowing());
						}
					} else {
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						if (wv_day.getCurrentItem() > 27) {
							wv_day.setCurrentItem(27, isShowing());
						}
					}
				}
			}
		};

		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		wv_hours.setAdapter(new NumericWheelAdapter(0, 23, "%02d"));

		wv_hours.setCyclic(true);

		wv_hours.setCurrentItem(hour);

		wv_mins.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));

		wv_mins.setCyclic(true);

		wv_mins.setCurrentItem(minute);

		int textSize = 0;

		textSize = 12;

		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;

		Button btn_sure = (Button) view.findViewById(R.id.btn_datetime_sure);
		Button btn_cancel = (Button) view.findViewById(R.id.btn_datetime_cancel);

		btn_sure.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (ymd == true)
					mCallBack.onDateSet(wv_year.getCurrentItem() + START_YEAR, wv_month.getCurrentItem(),
							wv_day.getCurrentItem() + 1);
				if (dm == true)
					mCallBack.onTimeSet(wv_hours.getCurrentItem(), wv_mins.getCurrentItem());
				dismiss();
			}
		});

		btn_cancel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if (null != mCallBackDismiss) {
					mCallBackDismiss.onDismiss();
				}
				dismiss();
			}
		});
		setContentView(view);
		return this;
	}
}
