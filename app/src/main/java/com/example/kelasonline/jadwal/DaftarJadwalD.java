package com.example.kelasonline.jadwal;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kelasonline.R;
import com.example.kelasonline.adapter.JadwalAdapterD;
import com.example.kelasonline.dosen.HomeDosen;
import com.example.kelasonline.model.Jadwal;
import com.example.kelasonline.util.api.BaseApiService;
import com.example.kelasonline.util.api.UtilsApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class DaftarJadwalD extends AppCompatActivity {
    TextView tvNama, tvEmail;
    String resultnama, resultemail;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private JadwalAdapterD adapter;
    private List<Jadwal> jadwalList;
    BaseApiService apiInterface;
    JadwalAdapterD.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_jadwald);
        getSupportActionBar().setTitle("Daftar Jadwal");
        apiInterface = UtilsApi.getAPIService();
        tvNama = (TextView) findViewById(R.id.tvNama);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        listener = new JadwalAdapterD.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();
                Intent intent = new Intent(DaftarJadwalD.this, EditorJadwal.class);
                intent.putExtra("id", jadwalList.get(position).getId());
                intent.putExtra("kelas", jadwalList.get(position).getKelas());
                intent.putExtra("nama_dosen", jadwalList.get(position).getNama_dosen());
                intent.putExtra("nama_matkul", jadwalList.get(position).getNama_matkul());
                intent.putExtra("result_nama", nama);
                intent.putExtra("result_email", email);
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(DaftarJadwalD.this, EditorJadwal.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            resultnama = extras.getString("result_nama");
            resultemail = extras.getString("result_email");

            tvNama.setText(resultnama);
            tvEmail.setText(resultemail);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Cari Mata Kuliah...");
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(DaftarJadwalD.this, HomeDosen.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getJadwal(){

        Call<List<Jadwal>> call = apiInterface.getJadwalDosen();
        call.enqueue(new Callback<List<Jadwal>>() {
            @Override
            public void onResponse(Call<List<Jadwal>> call, Response<List<Jadwal>> response) {
                progressBar.setVisibility(View.GONE);
                jadwalList = response.body();
                Log.i(DaftarJadwalD.class.getSimpleName(), response.body().toString());
                adapter = new JadwalAdapterD(jadwalList, DaftarJadwalD.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Jadwal>> call, Throwable t) {
                Toast.makeText(DaftarJadwalD.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getJadwal();
    }
}
