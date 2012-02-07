package jp.co.recruit.mtl.sample.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class Tweet implements Parcelable{
	private Long id;
	private String text;
	private String screenName;
	private String name;
	private String imageUrl;
	private String date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(text);
		dest.writeString(name);
		dest.writeString(screenName);
		dest.writeString(imageUrl);
		dest.writeString(date);
	}
	
	public static final Parcelable.Creator<Tweet> CREATOR = new Parcelable.Creator<Tweet>() {

		@Override
		public Tweet createFromParcel(Parcel source) {
			return new Tweet(source);
		}

		@Override
		public Tweet[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}

	};
	
	public Tweet(Parcel source) {
		id = source.readLong();
		text = source.readString();
		name = source.readString();
		screenName = source.readString();
		imageUrl = source.readString();
		date = source.readString();
	}
	
	public Tweet(){}
	
}
