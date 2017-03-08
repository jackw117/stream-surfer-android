package com.streamsurfer.surfers.streamsurfer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jack on 3/8/2017.
 */

public class UpdatedAdapter extends BaseAdapter {
    private String[] resultLeft;
    private String[] resultRight;
    private Context context;
    private int[] imageIdLeft;
    private int[] imageIdRight;
    private static LayoutInflater inflater = null;

    public UpdatedAdapter(Context activity, String[] titlesLeft, String[] titlesRight, int[] imagesLeft, int[] imagesRight) {
        resultLeft = titlesLeft;
        resultRight = titlesRight;
        context = activity;
        imageIdLeft = imagesLeft;
        imageIdRight = imagesRight;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return resultLeft.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.results_list, null);
        TextView tvl = (TextView) rowView.findViewById(R.id.title_left);
        TextView tvr = (TextView) rowView.findViewById(R.id.title_right);
        ImageView imgl = (ImageView) rowView.findViewById(R.id.image_left);
        ImageView imgr = (ImageView) rowView.findViewById(R.id.image_right);
        tvl.setText(resultLeft[position]);
        imgl.setImageResource(imageIdLeft[position]);
        if (resultRight[position] != null) {
            tvr.setText(resultRight[position]);
            imgr.setImageResource(imageIdRight[position]);
        } else {
            LinearLayout right = (LinearLayout) rowView.findViewById(R.id.right_layout_click);
            right.setVisibility(View.GONE);
            right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do nothing
                }
            });
        }
        return rowView;
    }
}
