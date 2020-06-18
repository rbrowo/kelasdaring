package com.example.kelasonline.materi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.kelasonline.R;
import com.example.kelasonline.model.Materi;
import com.example.kelasonline.util.api.BaseApiService;
import com.example.kelasonline.util.api.UtilsApi;
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

public class EditorMateriD extends AppCompatActivity {
    TextView tvNama, tvEmail;
    String resultnama, resultemail;
    private EditText etMatkul, etTopik, etMateri;
    private String matkul, topik, materi;
    private int id;
    private Menu action;

    private BaseApiService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_materid);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tvNama = (TextView) findViewById(R.id.tvNama);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        etMatkul = findViewById(R.id.etmatkul);
        etTopik = findViewById(R.id.ettopik);
        etMateri = findViewById(R.id.etmateri);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        matkul = intent.getStringExtra("matkul");
        topik = intent.getStringExtra("topik");
        materi = intent.getStringExtra("materi");

        setDataFromIntentExtra();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            resultnama = extras.getString("result_nama");
            resultemail = extras.getString("result_email");

            tvNama.setText(resultnama);
            tvEmail.setText(resultemail);
        }
    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            readMode();
            getSupportActionBar().setTitle("Edit Materi " + matkul.toString());

            etMatkul.setText(matkul);
            etTopik.setText(topik);
            etMateri.setText(materi);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.logo);
            requestOptions.error(R.drawable.logo);

        } else {
            getSupportActionBar().setTitle("Tambahkan Materi");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id == 0){

            action.findItem(R.id.menu_edit).setVisible(false);
            action.findItem(R.id.menu_delete).setVisible(false);
            action.findItem(R.id.menu_save).setVisible(true);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(EditorMateriD.this, DaftarMateriD.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);

                return true;
            case R.id.menu_edit:
                //Edit

                editMode();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etTopik, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;
            case R.id.menu_save:
                //Save

                if (id == 0) {

                    if (TextUtils.isEmpty(etMatkul.getText().toString()) ||
                            TextUtils.isEmpty(etTopik.getText().toString()) ||
                            TextUtils.isEmpty(etMateri.getText().toString()) ){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Silahkan isi semua kolom..");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                    else {

                        postData("insert");
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(true);

                        readMode();

                    }

                } else {

                    updateData("update", id);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;
            case R.id.menu_delete:

                AlertDialog.Builder dialog = new AlertDialog.Builder(EditorMateriD.this);
                dialog.setMessage("Hapus Materi?");
                dialog.setPositiveButton("Ya" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(EditorMateriD.this, DaftarMateriD.class));
                        deleteData("delete", id);
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void postData(final String key) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String matkul = etMatkul.getText().toString().trim();
        String topik = etTopik.getText().toString().trim();
        String materi = etMateri.getText().toString().trim();

        apiInterface = UtilsApi.getAPIService();

        Call<Materi> call = apiInterface.tambahMateri(key, matkul, topik, materi);

        call.enqueue(new Callback<Materi>() {
            @Override
            public void onResponse(Call<Materi> call, Response<Materi> response) {

                progressDialog.dismiss();

                Log.i(EditorMateriD.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    startActivity(new Intent(EditorMateriD.this, DaftarMateriD.class));
                    String nama = tvNama.getText().toString();
                    String email = tvEmail.getText().toString();

                    Intent i = new Intent(EditorMateriD.this, DaftarMateriD.class);
                    i.putExtra("result_nama", nama);
                    i.putExtra("result_email", email);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(EditorMateriD.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Materi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorMateriD.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Memperbarui...");
        progressDialog.show();

        readMode();

        String matkul = etMatkul.getText().toString().trim();
        String topik = etTopik.getText().toString().trim();
        String materi = etMateri.getText().toString().trim();

        apiInterface = UtilsApi.getAPIService();

        Call<Materi> call = apiInterface.updateMateri(key, id, matkul, topik, materi);

        call.enqueue(new Callback<Materi>() {
            @Override
            public void onResponse(Call<Materi> call, Response<Materi> response) {

                progressDialog.dismiss();

                Log.i(EditorMateriD.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(EditorMateriD.this, message, Toast.LENGTH_SHORT).show();
                    String nama = tvNama.getText().toString();
                    String email = tvEmail.getText().toString();

                    Intent i = new Intent(EditorMateriD.this, DaftarMateriD.class);
                    i.putExtra("result_nama", nama);
                    i.putExtra("result_email", email);
                    startActivity(i);
                } else {
                    Toast.makeText(EditorMateriD.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Materi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorMateriD.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterface = UtilsApi.getAPIService();

        Call<Materi> call = apiInterface.hapusMateri(key, id);

        call.enqueue(new Callback<Materi>() {
            @Override
            public void onResponse(Call<Materi> call, Response<Materi> response) {

                progressDialog.dismiss();

                Log.i(EditorMateriD.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(EditorMateriD.this, message, Toast.LENGTH_SHORT).show();
                    String nama = tvNama.getText().toString();
                    String email = tvEmail.getText().toString();

                    Intent i = new Intent(EditorMateriD.this, DaftarMateriD.class);
                    i.putExtra("result_nama", nama);
                    i.putExtra("result_email", email);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(EditorMateriD.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Materi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorMateriD.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    void readMode(){

        etMatkul.setFocusableInTouchMode(false);
        etTopik.setFocusableInTouchMode(false);
        etMateri.setFocusableInTouchMode(false);
    }

    private void editMode(){

        etMatkul.setFocusableInTouchMode(true);
        etTopik.setFocusableInTouchMode(true);
        etMateri.setFocusableInTouchMode(true);
    }
}
