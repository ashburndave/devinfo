package com.example.devinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView textView = (TextView) findViewById(R.id.myView);
		//		String s = getRuntimeInfo() + "\n\n" + getSystemInfo() + "\n\n"
		//				+ getTimeZoneInfo();
		String s = getSystemInfo();
		textView.setText(s);
		textView.setMovementMethod(new ScrollingMovementMethod());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);		
		String [] sa = {"SysProps", "Runtime Info", "Display Info",
				"TimeZones", "Countries", "Languages", "Locale Info"};
		for (int i = 0 ; i < sa.length ; i++) {
			MenuItem menuItem = menu.add(0, i, i, sa[i]);
			menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//return super.onOptionsItemSelected(item);
		return MenuChoice(item);
	}

	private boolean MenuChoice(MenuItem item) {
		TextView textView = (TextView) findViewById(R.id.myView);
		switch (item.getItemId()) {
		case 0:
			textView.setText(getSystemInfo());
			//textView.setScrollY(0); // scroll to the top of the text (req API 14)
			return true;
		case 1:
			textView.setText(getRuntimeInfo());
			//textView.setScrollY(0); // scroll to the top of the text (req API 14)
			return true;
		case 2:
			textView.setText(getDisplayInfo());
			//textView.setScrollY(0); // scroll to the top of the text (req API 14)
			return true;
		case 3:
			textView.setText(getTimeZoneInfo());
			//textView.setScrollY(0); // scroll to the top of the text (req API 14)
			return true;
		case 4:
			textView.setText(getCountryInfo());
			//textView.setScrollY(0); // scroll to the top of the text (req API 14)
			return true;
		case 5:
			textView.setText(getLanguageInfo());
			//textView.setScrollY(0); // scroll to the top of the text (req API 14)
			return true;
		case 6:
			textView.setText(getLocaleInfo());
			//textView.setScrollY(0); // scroll to the top of the text (req API 14)
			return true;
		}
		return false;

	}

	public String getSystemInfo() {
		StringBuffer sb = new StringBuffer();
		Properties sysProps = System.getProperties();
		Set<Object> keySet = sysProps.keySet();
		// List <String> keyList = new ArrayList<String>(keySet);
		List<String> keyList = new ArrayList<String>();
		// keyList.addAll(keySet);
		for (Object obj : keySet) {
			keyList.add((String) obj);
		}
		Collections.sort(keyList);
		for (String key : keyList) {
			sb.append(key);
			sb.append(" = ");
			sb.append(sysProps.getProperty(key));
			sb.append("\n");
			System.out.println(key + " = " + sysProps.getProperty(key));
		}
		return sb.toString();
	}

	public String getRuntimeInfo() {
		StringBuffer sb = new StringBuffer();
		Runtime runtime = Runtime.getRuntime();
		sb.append("runtime.availableProcessors() = "
				+ runtime.availableProcessors());
		sb.append("\nruntime.freeMemory() = " + runtime.freeMemory());
		sb.append("\nruntime.maxMemory() = " + runtime.maxMemory());
		sb.append("\nruntime.totalMemory() = " + runtime.totalMemory());
		sb.append("\n");
		return sb.toString();
	}

	public String getDisplayInfo() {
		StringBuffer sb = new StringBuffer();
		WindowManager wm = getWindowManager();
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		sb.append("display.getDisplayId() = " + display.getDisplayId());
		//sb.append("\ndisplay.getFlags() = " + display.getFlags()); // req API 17
		sb.append("\ndisplay.getHeight() = " + display.getHeight()); // deprecated
		//sb.append("\ndisplay.getName() = " + display.getName()); // req API 17
		sb.append("\ndisplay.getOrientation() = " + display.getOrientation()); // deprecated
		sb.append("\ndisplay.getPixelFormat() = " + display.getPixelFormat()); // deprecated
		sb.append("\ndisplay.getRefreshRate() = " + display.getRefreshRate());
		sb.append("\ndisplay.getRotation() = " + display.getRotation());
		sb.append("\ndisplay.getWidth() = " + display.getWidth()); // deprecated
		sb.append("\ndisplay.toString() = " + display.toString());
		sb.append("\nmetrics.density = " + metrics.density);
		sb.append("\nmetrics.densityDpi = " + metrics.densityDpi);
		sb.append("\nmetrics.heightPixels = " + metrics.heightPixels);
		sb.append("\nmetrics.scaledDensity = " + metrics.scaledDensity);
		sb.append("\nmetrics.widthPixels = " + metrics.widthPixels);
		sb.append("\nmetrics.xdpi = " + metrics.xdpi);
		sb.append("\nmetrics.ydpi = " + metrics.ydpi);
		sb.append("\nmetrics.toString() = " + metrics.toString());

		sb.append("\nDisplayMetrics.DENSITY_DEFAULT = " + DisplayMetrics.DENSITY_DEFAULT);
		sb.append("\nDisplayMetrics.DENSITY_HIGH = " + DisplayMetrics.DENSITY_HIGH);
		sb.append("\nDisplayMetrics.DENSITY_LOW = " + DisplayMetrics.DENSITY_LOW);
		sb.append("\nDisplayMetrics.DENSITY_MEDIUM = " + DisplayMetrics.DENSITY_MEDIUM);
		sb.append("\nDisplayMetrics.DENSITY_TV = " + DisplayMetrics.DENSITY_TV);
		sb.append("\nDisplayMetrics.DENSITY_XHIGH = " + DisplayMetrics.DENSITY_XHIGH);
		sb.append("\nDisplayMetrics.DENSITY_XXHIGH = " + DisplayMetrics.DENSITY_XXHIGH);

		Point outSmallestSize = new Point();
		Point outLargestSize = new Point();		
		//display.getCurrentSizeRange(outSmallestSize, outLargestSize); // req API 16
		sb.append("\ngetCurrentSizeRange outSmallestSize = " + outSmallestSize);
		sb.append("\ngetCurrentSizeRange outLargestSize = " + outLargestSize);		
		Point outSizePoint = new Point();
		//display.getRealSize(outSizePoint); // req API 17
		sb.append("\ngetRealSize outSizePoint = " + outSizePoint);
		Rect outSizeRect = new Rect();
		//display.getRectSize(outSizeRect); // req API 13
		sb.append("\ngetRectSize outSizeRect = " + outSizeRect);
		//display.getSize(outSizePoint); // req API 13
		sb.append("\ngetSize outSizePoint = " + outSizePoint);

		sb.append("\n");
		return sb.toString();
	}

	public String getDateInfo() {
		return "still a work in progress";
	}

	public String getTimeZoneInfo() {
		StringBuffer sb = new StringBuffer();
		String[] tziddArray = TimeZone.getAvailableIDs();
		List<String> tzidList = Arrays.asList(tziddArray);
		Collections.sort(tzidList);
		int maxLen = 0;
		for (String tzid : tzidList) {
			maxLen = Math.max(maxLen, tzid.length());
		}
		sb.append("TimeZone.getAvailableIDs().length = " + tzidList.size() + "\n");
		sb.append("TimeZone.getDefault().getID() = " + TimeZone.getDefault().getID() + "\n");
		sb.append("the longest TimeZone id is " + maxLen + " chars\n");
		for (String tzid : tzidList) {
			sb.append(tzid).append("\n");
		}
		return sb.toString();
	}

	public String getLocaleInfoOld() {
		// add code to show the current country
		StringBuffer sb = new StringBuffer();
		Locale[] localeArray = Locale.getAvailableLocales();
		List<Locale> localeList = Arrays.asList(localeArray);			
		//Collections.sort(localeList); // I will have to make my own Class that implements comparable		
		for (Locale locale : localeArray) {
			String country = locale.getCountry();
			String displayCountry = locale.getDisplayCountry();
			String displayName = locale.getDisplayName();
			String language = locale.getLanguage();
			String displayLanguage = locale.getDisplayLanguage();

			// and there are still more fields ...

			sb.append(displayName + " : " + country + " " + displayCountry +
					" : " + language + " " + displayLanguage + "\n");

		}
		return sb.toString();
	}

	public String getCountryInfoOld() {
		// add code to show the current country
		StringBuffer sb = new StringBuffer();
		String[] isoCountryArray = Locale.getISOCountries();
		List<String> isoCountryList = Arrays.asList(isoCountryArray);
		Collections.sort(isoCountryList);
		int maxLen = 0;
		for (String isoCountry : isoCountryList) {
			maxLen = Math.max(maxLen, isoCountry.length());
		}
		sb.append("Locale.getISOCountries().length = " + isoCountryList.size() + "\n");
		sb.append("Locale.getDefault().getCountry() = " + Locale.getDefault().getCountry() + "\n");
		sb.append("Locale.getDefault().getDisplayCountry() = " + Locale.getDefault().getDisplayCountry() + "\n");
		sb.append("the longest ISO Country name is " + maxLen + " chars\n");
		for (String isoCountry : isoCountryList) {
			sb.append(isoCountry).append("\n");
		}
		return sb.toString();
	}

	public String getLanguageInfoOld() {
		// add code to show the current language
		StringBuffer sb = new StringBuffer();
		String[] isoLanguageArray = Locale.getISOLanguages();
		List<String> isoLanguageList = Arrays.asList(isoLanguageArray);
		Collections.sort(isoLanguageList);
		int maxLen = 0;
		for (String isoLanguage : isoLanguageList) {
			maxLen = Math.max(maxLen, isoLanguage.length());
		}
		sb.append("Locale.getISOCountries().length = " + isoLanguageList.size() + "\n");
		sb.append("the longest ISO Language name is " + maxLen + " chars\n");
		for (String isoLanguage : isoLanguageList) {
			sb.append(isoLanguage).append("\n");
		}
		return sb.toString();
	}

	public String getLocaleInfo() {		
		StringBuffer sb = new StringBuffer();
		Locale [] localeArray = Locale.getAvailableLocales();	
		// sb.append("Locale.getISOLanguages().length = " + Locale.getISOLanguages().length + "\n");
		sb.append("Locale.getDefault().getDisplayName() = " + Locale.getDefault().getDisplayName() + "\n");
		Map <String,Locale> languageMap = new HashMap<String,Locale>();
		List <String> keyList;
		String sortField;		
		for (Locale locale : localeArray) {
			sortField = locale.getDisplayName();
			if (sortField != null && sortField.trim().length() > 0) {
				languageMap.put(sortField,locale);	
			}
		}
		keyList = new ArrayList<String>(languageMap.keySet());
		sb.append("locale count = " + keyList.size() + "\n");
		Collections.sort(keyList);
		int i = 1;
		for (String key : keyList) {
			sb.append(i++).append(" ");
			Locale locale = languageMap.get(key);
			String displayName = locale.getDisplayName();
			String country = locale.getCountry();
			String displayCountry = locale.getDisplayCountry();
			String language = locale.getLanguage();
			String displayLanguage = locale.getDisplayLanguage();
			// and there are still more fields ...
			sb.append(displayName + " : " + country + " " + displayCountry +
					" : " + language + " " + displayLanguage + "\n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public String getLanguageInfo() {		
		StringBuffer sb = new StringBuffer();
		Locale [] localeArray = Locale.getAvailableLocales();	
		// sb.append("Locale.getISOLanguages().length = " + Locale.getISOLanguages().length + "\n");
		sb.append("Locale.getDefault().getDisplayLanguage() = " + Locale.getDefault().getDisplayLanguage() + "\n");
		sb.append("Locale.getDefault().getLanguage() = " + Locale.getDefault().getLanguage() + "\n");
		sb.append("Locale.getDefault().getISO3Language() = " + Locale.getDefault().getISO3Language() + "\n");
		Map <String,Locale> languageMap = new HashMap<String,Locale>();
		List <String> keyList;
		String sortField;		
		for (Locale locale : localeArray) {
			sortField = locale.getDisplayLanguage();
			if (sortField != null && sortField.trim().length() > 0) {
				languageMap.put(sortField,locale);	
			}
		}
		keyList = new ArrayList<String>(languageMap.keySet());
		sb.append("language count = " + keyList.size() + "\n");
		Collections.sort(keyList);
		int i = 1;
		for (String key : keyList) {
			sb.append(i++).append(" ");
			Locale locale = languageMap.get(key);
			sb.append(locale.getDisplayLanguage() + " " + locale.getLanguage() + " " +
					locale.getISO3Language() + "\n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String getCountryInfo() {		
		StringBuffer sb = new StringBuffer();
		Locale [] localeArray = Locale.getAvailableLocales();	
		// sb.append("Locale.getISOLanguages().length = " + Locale.getISOLanguages().length + "\n");
		sb.append("Locale.getDefault().getDisplayCountry() = " + Locale.getDefault().getDisplayCountry() + "\n");
		sb.append("Locale.getDefault().getCountry() = " + Locale.getDefault().getCountry() + "\n");
		sb.append("Locale.getDefault().getISO3Country() = " + Locale.getDefault().getISO3Country() + "\n");
		Map <String,Locale> countryMap = new HashMap<String,Locale>();
		List <String> keyList;
		String sortField;		
		for (Locale locale : localeArray) {
			sortField = locale.getDisplayCountry();
			if (sortField != null && sortField.trim().length() > 0) {
				countryMap.put(sortField,locale);	
			}
		}
		keyList = new ArrayList<String>(countryMap.keySet());
		sb.append("country count = " + keyList.size() + "\n");
		Collections.sort(keyList);
		int i = 1;
		for (String key : keyList) {
			sb.append(i++).append(" ");
			Locale locale = countryMap.get(key);
			sb.append(locale.getDisplayCountry() + " " + locale.getCountry() + " " +
					locale.getISO3Country() + "\n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

}
