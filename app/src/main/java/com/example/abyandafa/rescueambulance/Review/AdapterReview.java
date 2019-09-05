package com.example.abyandafa.rescueambulance.Review;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.abyandafa.rescueambulance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abyan Dafa on 17/12/2017.
 */

public class AdapterReview  extends RecyclerView.Adapter<AdapterReview.ViewHolder> implements Filterable {

    private List<Keterlibatan> daftarKeterlibatan;
    private List<Keterlibatan> daftarKeterlibatanFilter;
    private AdapterReviewListener listener;

    public AdapterReview(List<Keterlibatan> daftarKeterlibatan, AdapterReviewListener listener) {
        this.daftarKeterlibatan = daftarKeterlibatan;
        this.daftarKeterlibatanFilter = daftarKeterlibatan;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_review, parent, false);
        return new ViewHolder(v);

    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Keterlibatan keterlibatan = daftarKeterlibatanFilter.get(position);
        holder.lokasiKejadian.setText(keterlibatan.getLokasi());
        holder.tanggalKejadian.setText(keterlibatan.getCreated_at());
        if(keterlibatan.getRating_ambulan() ==null)
        {
            float rating = 0;
            holder.ratingBar.setRating(rating);
            holder.noReview.setVisibility(View.VISIBLE);
            holder.ratingBar.setVisibility(View.GONE);
            holder.selected.setBackgroundColor(Color.parseColor("#fc5d5d"));
        }
        else
        {
            holder.ratingBar.setVisibility(View.VISIBLE);
            holder.noReview.setVisibility(View.GONE);
            holder.ratingBar.setRating(Float.valueOf(keterlibatan.getRating_ambulan()));
            holder.selected.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onReviewSelected(daftarKeterlibatanFilter.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return daftarKeterlibatanFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty())
                {
                    daftarKeterlibatanFilter = daftarKeterlibatan;
                }
                else
                {
                    List<Keterlibatan> filteredList = new ArrayList<>();
                    for(Keterlibatan row : daftarKeterlibatan)
                    {
                        if(row.getLokasi().toLowerCase().contains(charString.toLowerCase()) || row.getCreated_at().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    daftarKeterlibatanFilter = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = daftarKeterlibatanFilter;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                daftarKeterlibatanFilter = (ArrayList<Keterlibatan>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView lokasiKejadian, tanggalKejadian;
        public RatingBar ratingBar;
        public ImageView selected;
        public TextView noReview;


        public ViewHolder(View itemView) {
            super(itemView);
            lokasiKejadian = (TextView) itemView.findViewById(R.id.tampilan_lokasi);
            tanggalKejadian = (TextView) itemView.findViewById(R.id.tampilan_waktu);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating_tampilan);
            selected = (ImageView) itemView.findViewById(R.id.selek);
            noReview = (TextView) itemView.findViewById(R.id.no_review);




        }
    }

    public interface AdapterReviewListener{
        void onReviewSelected(Keterlibatan keterlibatan);
    }
}
