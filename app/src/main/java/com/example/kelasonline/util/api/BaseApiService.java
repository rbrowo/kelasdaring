package com.example.kelasonline.util.api;

import com.example.kelasonline.model.Dosen;
import com.example.kelasonline.model.Jadwal;
import com.example.kelasonline.model.Mahasiswa;
import com.example.kelasonline.model.Materi;
import com.example.kelasonline.model.TanyaJawab;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public interface BaseApiService {
    //Mahasiswa
    @FormUrlEncoded
    @POST("loginMhs.php")
    Call<ResponseBody>loginMhsRequest(@Field("nim") String nim,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("registerMhs.php")
    Call<ResponseBody>daftarMhsRequest(@Field("nim") String nim,
                                    @Field("nama") String nama,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @GET("get_mhs.php")
    Call<List<Mahasiswa>> getMahasiswa(
            @Query("key") String keyword);

    //Dosen
    @FormUrlEncoded
    @POST("loginDosen.php")
    Call<ResponseBody>loginDosenRequest(@Field("id_dosen") String id_dosen,
                                        @Field("password") String password);

    @FormUrlEncoded
    @POST("registerDosen.php")
    Call<ResponseBody>daftarDosenRequest(@Field("id_dosen") String id_dosen,
                                         @Field("nama") String nama,
                                         @Field("jenis_kelamin") String jenis_kelamin,
                                         @Field("email") String email,
                                         @Field("password") String password);

    @GET("get_dosen.php")
    Call<List<Dosen>> getDosen(
            @Query("key") String keyword);

    //Jadwal
    @GET("get_jadwalM.php")
    Call<List<Jadwal>> getJadwalMahasiswa(
            @Query("key") String keyword);

    @POST("get_jadwalD.php")
    Call<List<Jadwal>> getJadwalDosen();

    @FormUrlEncoded
    @POST("insert_jadwal.php")
    Call<Jadwal> tambahJadwal(@Field("key") String key,
                              @Field("kelas") String kelas,
                              @Field("nama_dosen") String nama_dosen,
                              @Field("nama_matkul") String nama_matkul);

    @FormUrlEncoded
    @POST("update_jadwal.php")
    Call<Jadwal> updateJadwal(@Field("key") String key,
                              @Field("id") int id,
                              @Field("kelas") String kelas,
                              @Field("nama_dosen") String nama_dosen,
                              @Field("nama_matkul") String nama_matkul);

    @FormUrlEncoded
    @POST("delete_jadwal.php")
    Call<Jadwal> hapusJadwal(@Field("key") String key,
                             @Field("id") int id);

    //Materi
    @POST("get_materi.php")
    Call<List<Materi>> getMateri();

    @FormUrlEncoded
    @POST("insert_materi.php")
    Call<Materi> tambahMateri(@Field("key") String key,
                              @Field("matkul") String matkul,
                              @Field("topik") String topik,
                              @Field("materi") String materi);

    @FormUrlEncoded
    @POST("update_materi.php")
    Call<Materi> updateMateri(@Field("key") String key,
                              @Field("id") int id,
                              @Field("matkul") String matkul,
                              @Field("topik") String topik,
                              @Field("materi") String materi);

    @FormUrlEncoded
    @POST("delete_materi.php")
    Call<Materi> hapusMateri(@Field("key") String key,
                             @Field("id") int id);

    //TanyaJawab
    @POST("get_tanya.php")
    Call<List<TanyaJawab>> getPertanyaan();

    @FormUrlEncoded
    @POST("insert_tanyaD.php")
    Call<TanyaJawab> tambahTanyaD(@Field("key") String key,
                              @Field("subjek") String subjek,
                              @Field("mahasiswa") String mahasiswa,
                              @Field("dosen") String dosen,
                              @Field("matkul") String matkul,
                              @Field("pertanyaan") String pertanyaan);

    @FormUrlEncoded
    @POST("insert_tanya.php")
    Call<TanyaJawab> tambahPertanyaan(@Field("subjek") String subjek,
                                      @Field("mahasiswa") String mahasiswa,
                                      @Field("dosen") String dosen,
                                      @Field("matkul") String matkul,
                                      @Field("pertanyaan") String pertanyaan);

    @FormUrlEncoded
    @POST("update_jawab.php")
    Call<TanyaJawab> updateJawaban(@Field("key") String key,
                                   @Field("id") int id,
                                   @Field("subjek") String subjek,
                                   @Field("mahasiswa") String mahasiswa,
                                   @Field("dosen") String dosen,
                                   @Field("matkul") String matkul,
                                   @Field("pertanyaan") String pertanyaan,
                                   @Field("jawaban") String jawaban);

    @FormUrlEncoded
    @POST("delete_tanya.php")
    Call<TanyaJawab> hapusPertanyaan(@Field("key") String key,
                                     @Field("id") int id);
}