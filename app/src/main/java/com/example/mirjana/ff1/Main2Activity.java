package com.example.mirjana.ff1;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Page where the user can fill out the question survey
 * @author: Mirjana Mijalkovic
 * @version: 1.0
 * @date: June 7 2017
 */
public class Main2Activity extends AppCompatActivity {
    private Button Quizbtn;
    String r1 ;
    String r2 ;
    TextView tv;
    private CloudantClient client;
    private Database db;
    int total = 0;
    @Override

/**
 * When the page is opened, the surview is visible, along with the questions to be answered
 * Calls DownloadWebpageTask()
 * @param savedInstanceState
 */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Quizbtn = (Button) findViewById(R.id.Quizbtn);

        Quizbtn.setOnClickListener(new View.OnClickListener() { //editText onclick listener
            @Override
            public void onClick(View v) {

                r1 =  findViewById(R.id.response1).toString();
                r2 =  findViewById(R.id.response2).toString();
            }
        });
        // Added a textView object
        tv = (TextView) findViewById(R.id.tvOut);
        client = ClientBuilder.account("famfeud")
                .username("famfeud")
                .password("ICS4U2017")
                .build();
        new DownloadWebpageTask().execute();
    }

    /**
     * When activity is launched, current data from the Cloudant database is called to the application.
     */
    private void launchActivity() {
    new DownloadWebpageTask().execute();
}

    /**
     * Calls the Cloudant database and send it premade questions with an arraylist of responses
      */
    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // Get a List of all the databases this Cloudant account
            db = client.database("feudgame",true);
            QuestionClass p = new QuestionClass(1015,"Name an article of clothing you can’t wash in the washing machine");
            p.addAnswer(new AnswerClass(1, r1));
            p.addAnswer(new AnswerClass(1, r2));
            db.save(p);
            QuestionClass q = new QuestionClass(2015, "Most riskiest job");
            q.addAnswer(new AnswerClass(30,"food service"));
            q.addAnswer(new AnswerClass(20,"police officer"));
            q.addAnswer(new AnswerClass(10,"construction"));
           db.save(q);
 List<QuestionClass> allQuestions = new ArrayList<QuestionClass>();
            try {
                allQuestions = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(QuestionClass.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String out = "";
            for (QuestionClass quest : allQuestions){
//                Log.d("question", quest.getQuestion());
                for (AnswerClass ans : quest.getAnswers()){
                    total += ans.getNum();
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  Toast toast = Toast.makeText(getApplicationContext(), "Updated Database", Toast.LENGTH_SHORT);
                    toast.show();
                    tv.setText("Survey total was :" + String.valueOf(total));
                }
            });
            return "";
        }
    }
}
