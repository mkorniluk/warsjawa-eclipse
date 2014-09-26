package com.example.crappyapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Locale;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	SectionsPagerAdapter pagerAdapter;
	ViewPager viewPager;
	char[] allowed = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		pagerAdapter = new SectionsPagerAdapter();
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(pagerAdapter);

	}

	public class SectionsPagerAdapter extends PagerAdapter {
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View rootView = View.inflate(container.getContext(),
					R.layout.page, null);
			final ImageView imageIV = (ImageView) rootView
					.findViewById(R.id.image);

			
			String color = "";
            		for (int i = 0; i < 6; i++) {
                		color += allowed[new Random().nextInt(allowed.length)];
            		}

			new AsyncTask<String, Void, Bitmap>() {
				protected Bitmap doInBackground(String... url) {
					Bitmap bitmap = null;
					try {
						bitmap = BitmapFactory.decodeStream(new URL(url[0])
								.openConnection().getInputStream());
					} catch (MalformedURLException e) {
					} catch (IOException e) {
					}
					return bitmap;
				}

				protected void onPostExecute(Bitmap result) {
					imageIV.setImageBitmap(result);
				}
			}.execute(new String[] { "http://placehold.it/1920x1080/" + color });

			TextView labelTV = (TextView) rootView.findViewById(R.id.label);
			String label = "This is an awesome picture of a furry cat no. "
					+ position;
			labelTV.setText(label);
			container.addView(rootView);
			return rootView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}

		@Override
		public int getCount() {
			return 16;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

}
