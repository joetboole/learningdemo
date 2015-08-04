package demo.joez.floatlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import demo.joez.com.demolist.R;

public class PinnedSectionListView extends ListView {

	private Context context;
	private PinnedSectionLvAdapter adapter;
	private View recyclePinnedHeader;
	private View currentPinnedHeader;
	private int mTranslateY;
	private boolean isPinnedHeaderShown;

	public PinnedSectionListView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public PinnedSectionListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	public PinnedSectionListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		initView();
	}

	private void initView() {
		isPinnedHeaderShown = false;
		adapter = new PinnedSectionLvAdapter(context);
		this.setAdapter(adapter);
		this.setOnScrollListener(mOnScrollListener);
	}


	private void createPinnedHeader(int position) {
		
		View pinnedView = (TextView) adapter.getPinnedSectionView(position);

		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pinnedView
				.getLayoutParams();
		if (layoutParams == null) {
			layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		}

		int heightMode = MeasureSpec.getMode(layoutParams.height);
		int heightSize = MeasureSpec.getSize(layoutParams.height);

		if (heightMode == MeasureSpec.UNSPECIFIED)
			heightMode = MeasureSpec.EXACTLY;

		int maxHeight = getHeight() - getListPaddingTop()
				- getListPaddingBottom();
		if (heightSize > maxHeight)
			heightSize = maxHeight;

		int ws = MeasureSpec.makeMeasureSpec(getWidth() - getListPaddingLeft()
				- getListPaddingRight(), MeasureSpec.EXACTLY);
		int hs = MeasureSpec.makeMeasureSpec(heightSize, heightMode);
		pinnedView.measure(ws, hs);
		pinnedView.layout(0, 0, pinnedView.getMeasuredWidth(),
				pinnedView.getMeasuredHeight());
		
		currentPinnedHeader = pinnedView;
	}

	private OnScrollListener mOnScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {

		}


		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			MyData myData = adapter.getItem(firstVisibleItem);
			MyData nextData = adapter.getItem(firstVisibleItem + 1);

			if (!myData.firstLetter.equals(nextData.firstLetter)) {
				View childView = view.getChildAt(0);
				if (childView != null) {
					mTranslateY = childView.getTop();
					createPinnedHeader(firstVisibleItem);
					recyclePinnedHeader = currentPinnedHeader;
					System.out.println("ding ... " + mTranslateY);
				}
			} else {
				
				if(currentPinnedHeader != null && isPinnedHeaderShown) {
					currentPinnedHeader = recyclePinnedHeader;
					mTranslateY = 0;
					System.out.println("recycle");
					return;
				}
				
				createPinnedHeader(firstVisibleItem);
				recyclePinnedHeader = currentPinnedHeader;
				System.out.println("create");
			}
		}
	};


	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);

		if (currentPinnedHeader != null) {
			View pinnedView = currentPinnedHeader;

			int pLeft = getListPaddingLeft();
			int pTop = getListPaddingTop();

			canvas.save();
			canvas.clipRect(pLeft, pTop, pLeft + pinnedView.getWidth(),
					pTop + pinnedView.getHeight());
			canvas.translate(pLeft, pTop + mTranslateY);
			drawChild(canvas, pinnedView, getDrawingTime());
			canvas.restore();
			
			isPinnedHeaderShown = true;
		}
	}


	public class PinnedSectionLvAdapter extends BaseAdapter {

		private Context context;
		private ArrayList<MyData> datas;
		public Map<String, Integer> maps;

		public PinnedSectionLvAdapter(Context context) {
			this.context = context;
			this.datas = MyData.getData();
			sortLetter(datas);
		}

		public PinnedSectionLvAdapter(Context context, ArrayList<MyData> datas) {
			this.context = context;
			this.datas = datas;
			sortLetter(datas);
		}


		public View getPinnedSectionView(int position) {
			ViewGroup view = (ViewGroup) getView(position, null,
					PinnedSectionListView.this);
			View vAlpha = view.getChildAt(0);
			return vAlpha;
		}

		@Override
		public int getCount() {
			return datas.size();
		}

		@Override
		public MyData getItem(int position) {
			return datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(context, R.layout.alpha_item, null);
			final TextView tvAlpha = (TextView) view
					.findViewById(R.id.alphaitem_tv_alpha);
			TextView tvContent = (TextView) view
					.findViewById(R.id.alphaitem_tv_content);

			MyData myData = getItem(position);

			tvAlpha.setText(myData.firstLetter);
			if (maps.get(myData.firstLetter) == position) {
				tvAlpha.setVisibility(View.VISIBLE);
			} else {
				tvAlpha.setVisibility(View.GONE);
			}
			tvAlpha.setTag(position);

			tvContent.setText(datas.get(position).data);

			return view;
		}

		private void sortLetter(ArrayList<MyData> datas) {
			Collections.sort(datas, new Comparator<MyData>() {
				@Override
				public int compare(MyData lhs, MyData rhs) {
					return lhs.firstLetter.compareTo(rhs.firstLetter);
				}
			});

			maps = new HashMap<String, Integer>();
			for (int i = 0; i < datas.size(); i++) {
				if (!maps.containsKey(datas.get(i).firstLetter)) {
					maps.put(datas.get(i).firstLetter, i);
				}
			}
		}
	}
	
	class PinnedHeader {
		public View view;
		public int position;
		@Override
		public String toString() {
			return "PinnedHeader [view=" + view + ", position=" + position
					+ "]";
		}
	}

}


class MyData {
	public String firstLetter;
	public String data;

	@Override
	public String toString() {
		return "MyData [firstLetter=" + firstLetter + ", data=" + data + "]";
	}

	public static ArrayList<MyData> getData() {
		ArrayList<MyData> datas = new ArrayList<MyData>();
		for (int i = 0; i < 10; i++) {
			MyData data = new MyData();
			data.firstLetter = "a";
			data.data = "a" + i;
			datas.add(data);
		}
		for (int i = 0; i < 10; i++) {
			MyData data = new MyData();
			data.firstLetter = "e";
			data.data = "e" + i;
			datas.add(data);
		}
		for (int i = 0; i < 20; i++) {
			MyData data = new MyData();
			data.firstLetter = "b";
			data.data = "b" + i;
			datas.add(data);
		}
		for (int i = 0; i < 10; i++) {
			MyData data = new MyData();
			data.firstLetter = "w";
			data.data = "w" + i;
			datas.add(data);
		}
		return datas;
	}
}
