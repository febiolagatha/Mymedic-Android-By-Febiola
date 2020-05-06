package com.example.tenagamedis;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.DaftarViewHolder> {
    private Context mContext;
    private Cursor mCursor;


    public DaftarAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class DaftarViewHolder extends RecyclerView.ViewHolder {
        public TextView namaText;
        public TextView noText;
        public TextView sakitText;
        public TextView kosasihText;

        public DaftarViewHolder(@NonNull View itemView) {
            super(itemView);

            namaText = itemView.findViewById(R.id.nama_list);
            noText = itemView.findViewById(R.id.no_list);
            sakitText = itemView.findViewById(R.id.sakit_list);
            kosasihText = itemView.findViewById(R.id.spinner_list);
        }
    }

    @NonNull
    @Override
    public DaftarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.daftar_item, parent, false);
        return new DaftarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DaftarViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String nama = mCursor.getString(mCursor.getColumnIndex(DaftarContract.DaftarEntry.COLUMN_1));
        String nomor = mCursor.getString(mCursor.getColumnIndex(DaftarContract.DaftarEntry.COLUMN_2));
        String penyakit = mCursor.getString(mCursor.getColumnIndex(DaftarContract.DaftarEntry.COLUMN_3));
        String kosasih = mCursor.getString(mCursor.getColumnIndex(DaftarContract.DaftarEntry.COLUMN_4));
        long id = mCursor.getLong(mCursor.getColumnIndex(DaftarContract.DaftarEntry._ID));

        holder.namaText.setText(nama);
        holder.noText.setText(nomor);
        holder.sakitText.setText(penyakit);
        holder.kosasihText.setText(kosasih);
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
