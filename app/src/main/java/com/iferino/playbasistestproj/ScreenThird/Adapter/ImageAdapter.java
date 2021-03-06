package com.iferino.playbasistestproj.ScreenThird.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iferino.playbasistestproj.R;
import com.iferino.playbasistestproj.ScreenThird.GalleryFragment;
import com.iferino.playbasistestproj.ScreenThird.Model.Image;
import com.squareup.picasso.Picasso;

import java.util.List;
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

private static List<Image>                             images;
private static Context                                 context;
private static GalleryFragment.OnImageSelectedListener callback;


public static class ImageViewHolder extends RecyclerView.ViewHolder {
	private ImageView img_view;

	public ImageViewHolder(View v) {
		super(v);
		img_view = (ImageView) v.findViewById(R.id.img_view);
		img_view.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				callback.onImageSelected(images.get(getAdapterPosition()).getUrl(),
				                         images.get(getAdapterPosition()).getPublicId());
			}
		});
	}
}

public ImageAdapter(List<Image> images, Context context, GalleryFragment.OnImageSelectedListener callback) {
	this.images = images;
	this.context = context;
	this.callback = callback;
	//pre-load
	for(Image img: images){
		Picasso.with(context).load(img.getUrl()).fetch();
	}
}

public void addAll(List<Image> images){
	this.images.addAll(0, images);
	notifyDataSetChanged();
}
@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
	return new ImageViewHolder(view);
}


@Override
public void onBindViewHolder(ImageViewHolder holder, final int position) {
	Picasso.with(context).load(images.get(position).getUrl()).noFade().fit().placeholder(R.drawable.ic_launcher)
			.into(holder.img_view);
}

@Override
public int getItemCount() {
	return images.size();
}
}