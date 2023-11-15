package co.edu.unal.androidsqlite.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.unal.androidsqlite.R;
import co.edu.unal.androidsqlite.ViewActivity;
import co.edu.unal.androidsqlite.entities.Companies;

public class ListCompaniesAdapter extends RecyclerView.Adapter<ListCompaniesAdapter.companyviewHolder> {

    ArrayList<Companies> listCompanies;

    ArrayList<Companies> completeList;

    public ListCompaniesAdapter(ArrayList<Companies> listCompanies){
        this.listCompanies = listCompanies;
        completeList = new ArrayList<>();
        completeList.addAll(listCompanies);
    }
    @NonNull
    @Override
    public companyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_companies,null,false);
        return new companyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull companyviewHolder holder, int position) {
        holder.viewName.setText(listCompanies.get(position).getName());
        holder.viewTelephone.setText(listCompanies.get(position).getTelefono());
        holder.viewMail.setText(listCompanies.get(position).getCorreo_electronico());

    }

    public void filter(String search){
        int size = search.length();

        if (size == 0){
            listCompanies.clear();
            listCompanies.addAll(completeList);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Companies> collection = listCompanies.stream().filter(i -> i.getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
                listCompanies.clear();
                listCompanies.addAll(collection);
            }else{
                for (Companies company: completeList) {
                    if(company.getName().toLowerCase().contains(search.toLowerCase())){
                        listCompanies.add(company);
                    }
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listCompanies.size();
    }

    public class companyviewHolder extends RecyclerView.ViewHolder {

        TextView viewName,viewTelephone,viewMail;
        public companyviewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.viewName);
            viewTelephone = itemView.findViewById(R.id.viewTelephone);
            viewMail = itemView.findViewById(R.id.viewMail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("ID",listCompanies.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
