package com.firsteconomy.nytapp.ui.test_stuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.network.RestClient;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestOneActivity extends AppCompatActivity {

    private String TAG = "TestOneActivity";
    private String encryptedText = "V4%2fUom%2bTputVq32VwdLirRtC4MThYWGLC%2f3wdNWtunIbg8hp5i2Cgl68vzwsenp2LtMKETky5A0tUhHPpQtBvupN2QPL2k4JgGdu1%2b9dHE%2f%2bxhrYL28J2dmlwowW5NtTPaqcsOYgzYnnW1LFAHmPeA4RQ6d82ROOlJf%2fraEjDEs1PDrlopKIhQiN567tpEZlvdhvka2ijLyqKMHzo%2bHEIPhP8il5SfV2Wa1vBciNXFk6hPeEjcnLLkaUtqZiD479oVBsat%2b4tVAwk0zPbU8ISazxD3Y7CrqDgfYQWgFByXxCf7Ca97WJMNkviVmhw2biqliPQCdpYnxR1Z9qKnqzs75C4T3zH1A8M8ZG%2b8Pj%2fCPNfIAR2ugyL8nB9RvIh0zK9ETFe6o2%2fLvODSVCsB4tJi3%2bbd97tnQfgeG7RaujEC%2fBrIg4ibzJ8W2IPYW9rNqjCSE8otuzevC29WwaL9ruTlbKxcVvuGis9tL%2bkrhovOWRsc4D7W3g9t0j1%2fahdO54E%2fyKo4GNYbi8JDGnv1uz5k8aZsOqvrLj27zPlaogIIX1QNERBpWpuqJXCJC97CnaM54mbwVMXbdLk5qjJx4rz%2buXGjvMJ3Awt3I4G7A76MW0f%2bPFE6%2b%2fzjkFZ3%2bxEWtbyvPIeqOcg3c1uOmWFNFCn4iLZwCQ8DrsIgJvBTtBjK8%3d";
    private String key = "OX5AZBMBBDYH4QIM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_one);
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        try {
            Log.e(TAG, "onCreate: decrypted text ---->   " + decryptText(Base64.decode(encryptedText, Base64.DEFAULT), secretKey));
        } catch (Exception e) {
            e.printStackTrace();
        }

        runWork();
    }

    private void runWork() {

        Constraints workConstraints =new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest
                .Builder(SampleWorker.class)
                .setConstraints(workConstraints)
                .build();
        WorkManager.getInstance().enqueue(workRequest);

    }

    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);

    }

    public void getTopStories(View view) {
        RestClient.webServices()
                .getTopStories_with_Call_returnType("home")
                .enqueue(new Callback<TopStoriesResponse>() {
                    @Override
                    public void onResponse(Call<TopStoriesResponse> call, Response<TopStoriesResponse> response) {
                        Log.e(TAG, "onResponse: " + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<TopStoriesResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: ");
                    }
                });
    }
}
