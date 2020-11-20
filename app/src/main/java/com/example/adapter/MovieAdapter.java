package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.douban.R;
import com.example.utils.Movie;

import java.util.List;

public class MovieAdapter extends ArrayAdapter {
   private final int resourceId;
    private List<Movie> mMovieList;
    public MovieAdapter(Context context, int textViewResourceId, List<Movie> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;

    }

   @Override
    public View getView(int arg0, View convertView, ViewGroup parent) {
       Movie movie = (Movie) getItem(arg0); // 获取当前项的Movie实例
       Boolean chboxall=false;
       View view;
       ViewHolder viewholder;
       final int position=arg0;
       if(convertView==null){
           view=LayoutInflater.from(getContext()).inflate(resourceId, null);
           viewholder=new ViewHolder();

           CheckBox movie_checkbox = (CheckBox) view.findViewById(R.id.stu_checkbox);
           viewholder.movieIntro=(TextView) view.findViewById(R.id.intro);
           viewholder.movieName=(TextView) view.findViewById(R.id.name);
           movie_checkbox.setChecked(chboxall);
           movie_checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                       @Override
                       public void onCheckedChanged(CompoundButton buttonView,
                                                    boolean isChecked) {
                           // TODO Auto-generated method stub
                           mMovieList.get(position).setChecked(isChecked);

                       }
                   });
           view.setTag(viewholder);
       }else{
           view=convertView;
           viewholder=(ViewHolder)view.getTag();
       }
       viewholder.movieIntro.setText(mMovieList.get(arg0).getIntroduction());
       viewholder.movieName.setText(mMovieList.get(arg0).getName());
       return view;
       /** View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
       ImageView movieImage = (ImageView) view.findViewById(R.id.pic);
        TextView movieName = (TextView) view.findViewById(R.id.name);
        TextView movieIntro = (TextView) view.findViewById(R.id.intro);
        movieImage.setImageResource(movie.getImageId());
        movieName.setText(movie.getName());
        movieIntro.setText(movie.getIntroduction());
        return view;**/
    }
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }



    class ViewHolder{
        TextView movieName;
        TextView movieIntro;
    }
}
