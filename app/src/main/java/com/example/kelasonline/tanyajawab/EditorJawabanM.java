package com.example.kelasonline.tanyajawab;

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
import com.example.kelasonline.jadwal.EditorJadwal;
import com.example.kelasonline.model.TanyaJawab;
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

public class EditorJawabanM extends AppCompatActivity {
    TextView tvNama, tvEmail;
    String resultnama, resultemail;
    private EditText etSubjek, etMhs, etDosen, etMatkul, etTanya, etJawaban;
    private String subjek, mhs, dosen, matkul, tanya, jawab;
    private int id;
    private Menu action;

    private BaseApiService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_jawabanm);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tvNama = (TextView) findViewById(R.id.tvNama);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        etSubjek = findViewById(R.id.subjek);
        etMhs = findViewById(R.id.mhs);
        etDosen = findViewById(R.id.dosen);
        etMatkul = findViewById(R.id.matkul);
        etTanya = findViewById(R.id.tanya);
        etJawaban = findViewById(R.id.etJawab);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        subjek = intent.getStringExtra("subjek");
        mhs = intent.getStringExtra("mahasiswa");
        dosen = intent.getStringExtra("dosen");
        matkul = intent.getStringExtra("matkul");
        tanya = intent.getStringExtra("pertanyaan");
        jawab = intent.getStringExtra("jawaban");

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
            getSupportActionBar().setTitle("Jawaban Pertanyaan " + mhs.toString());

            etSubjek.setText(subjek);
            etMhs.setText(mhs);
            etDosen.setText(dosen);
            etMatkul.setText(matkul);
            etTanya.setText(tanya);
            etJawaban.setText(jawab);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.logo);
            requestOptions.error(R.drawable.logo);

        } else {
            //getSupportActionBar().setTitle("Tambahkan Jawaban");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);
        action.findItem(R.id.menu_edit).setVisible(false);
        action.findItem(R.id.menu_delete).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(EditorJawabanM.this, DaftarTanyaM.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);

                return true;
            case R.id.menu_edit:
                //Edit

                editMode();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etSubjek, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(false);

                return true;
            case R.id.menu_save:
                //Save

                if (id == 0) {

                    if (TextUtils.isEmpty(etSubjek.getText().toString()) ||
                            TextUtils.isEmpty(etMhs.getText().toString()) ||
                            TextUtils.isEmpty(etDosen.getText().toString()) ||
                            TextUtils.isEmpty(etMatkul.getText().toString()) ||
                            TextUtils.isEmpty(etTanya.getText().toString()) ||
                            TextUtils.isEmpty(etJawaban.getText().toString())){
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
                        action.findItem(R.id.menu_edit).setVisible(false);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(false);

                        readMode();

                    }

                } else {

                    updateData("update", id);
                    action.findItem(R.id.menu_edit).setVisible(false);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(false);

                    readMode();
                }

                return true;
            case R.id.menu_delete:

                AlertDialog.Builder dialog = new AlertDialog.Builder(EditorJawabanM.this);
                dialog.setMessage("Hapus Jadwal?");
                dialog.setPositiveButton("Ya" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(EditorJawabanM.this, DaftarTanyaM.class));
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

        String subjek = etSubjek.getText().toString().trim();
        String mhs = etMhs.getText().toString().trim();
        String dosen = etDosen.getText().toString().trim();
        String matkul = etMatkul.getText().toString().trim();
        String tanya = etTanya.getText().toString().trim();

        apiInterface = UtilsApi.getAPIService();

        Call<TanyaJawab> call = apiInterface.tambahTanyaD(key, subjek, mhs, dosen, matkul, tanya);

        call.enqueue(new Callback<TanyaJawab>() {
            @Override
            public void onResponse(Call<TanyaJawab> call, Response<TanyaJawab> response) {

                progressDialog.dismiss();

                Log.i(EditorJadwal.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    finish();
                } else {
                    Toast.makeText(EditorJawabanM.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TanyaJawab> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorJawabanM.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Memperbarui...");
        progressDialog.show();

        readMode();

        String subjek = etSubjek.getText().toString().trim();
        String mhs = etMhs.getText().toString().trim();
        String dosen = etDosen.getText().toString().trim();
        String matkul = etMatkul.getText().toString().trim();
        String tanya = etTanya.getText().toString().trim();
        String jawab = etJawaban.getText().toString().trim();

        apiInterface = UtilsApi.getAPIService();

        Call<TanyaJawab> call = apiInterface.updateJawaban(key, id, subjek, mhs, dosen, matkul, tanya, jawab);

        call.enqueue(new Callback<TanyaJawab>() {
            @Override
            public void onResponse(Call<TanyaJawab> call, Response<TanyaJawab> response) {

                progressDialog.dismiss();

                Log.i(EditorJawabanD.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(EditorJawabanM.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditorJawabanM.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TanyaJawab> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorJawabanM.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterface = UtilsApi.getAPIService();

        Call<TanyaJawab> call = apiInterface.hapusPertanyaan(key, id);

        call.enqueue(new Callback<TanyaJawab>() {
            @Override
            public void onResponse(Call<TanyaJawab> call, Response<TanyaJawab> response) {

                progressDialog.dismiss();

                Log.i(EditorJawabanD.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(EditorJawabanM.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditorJawabanM.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TanyaJawab> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorJawabanM.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    void readMode(){

        etSubjek.setFocusableInTouchMode(false);
        etMhs.setFocusableInTouchMode(false);
        etDosen.setFocusableInTouchMode(false);
        etMatkul.setFocusableInTouchMode(false);
        etTanya.setFocusableInTouchMode(false);
        etJawaban.setFocusableInTouchMode(false);
    }

    private void editMode(){

        etSubjek.setFocusableInTouchMode(false);
        etMhs.setFocusableInTouchMode(false);
        etDosen.setFocusableInTouchMode(false);
        etMatkul.setFocusableInTouchMode(false);
        etTanya.setFocusableInTouchMode(false);
        etJawaban.setFocusableInTouchMode(true);
    }
}
