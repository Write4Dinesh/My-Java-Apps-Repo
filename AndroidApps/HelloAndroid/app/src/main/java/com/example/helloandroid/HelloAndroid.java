package com.example.helloandroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * Sample Android UI activity which displays a text window when it is run.
 */
public class HelloAndroid extends OrmLiteBaseActivity<DatabaseHelper> {

    private final String LOG_TAG = getClass().getSimpleName();
    DatabaseHelper mDatabaseHelper = null;
    private ListView mListView;
    WFMFavoriteOffersStoreManager mWFMFavoriteOffersStoreManager = null;
    ProfileListAdapter mListAdapter = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "creating " + getClass() + " at " + System.currentTimeMillis());
        TextView tv = new TextView(this);
        mDatabaseHelper = getHelper();
        // doSampleDatabaseStuff("onCreate", tv);
        setContentView(R.layout.main);
        mListView = (ListView) findViewById(R.id.profile_lv);
        Button saveButton = (Button) findViewById(R.id.save_button);
        Button removeButton = (Button) findViewById(R.id.remove_button);
        final EditText profileEt = (EditText) findViewById(R.id.profile_id_et);
        final EditText offerEt = (EditText) findViewById(R.id.offer_id_et);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String profileId = profileEt.getText().toString();
                String offerId = offerEt.getText().toString();
                save(profileId, offerId);
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String profileId = profileEt.getText().toString();
                remove(profileId);
            }
        });
        mWFMFavoriteOffersStoreManager = new WFMFavoriteOffersStoreManager(mDatabaseHelper);
        mListAdapter = new ProfileListAdapter(this, new ArrayList<OfferModel>());
        mListView.setAdapter(mListAdapter);
    }

    private void remove(String profileId) {
        mWFMFavoriteOffersStoreManager.removeFavoriteOfferForTheUser(profileId);
        WFMFavoriteOffersDO offersDO = mWFMFavoriteOffersStoreManager.getFavoriteOfferForTheUser(profileId);
        OfferModel model = new OfferModel();
        model.profileId = profileId;
        mListAdapter.removeItem(model);
        mListAdapter.notifyDataSetChanged();
    }

    private void save(String profileId, String offerId) {
        mWFMFavoriteOffersStoreManager.saveFavoriteOfferForTheUser(profileId, offerId);
        WFMFavoriteOffersDO offersDO = mWFMFavoriteOffersStoreManager.getFavoriteOfferForTheUser(profileId);
        OfferModel model = new OfferModel();
        model.profileId = offersDO.profile_id;
        for (String str : offersDO.offer_id_list) {
            model.offerId = model.offerId + "," + str;
        }
        mListAdapter.addItem(model);
        mListAdapter.notifyDataSetChanged();
    }

    /**
     * Do our sample database stuff.
     */
    private void doSampleDatabaseStuff(String action, TextView tv) {
        // get our dao
        RuntimeExceptionDao<SimpleData, Integer> simpleDao = mDatabaseHelper.getSimpleDataDao();
        // query for all of the data objects in the database
        List<SimpleData> list = simpleDao.queryForAll();
        // our string builder for building the content-view
        StringBuilder sb = new StringBuilder();
        sb.append("got ").append(list.size()).append(" entries in ").append(action).append("\n");

        // if we already have items in the database
        int simpleC = 0;
        for (SimpleData simple : list) {
            sb.append("------------------------------------------\n");
            sb.append("[").append(simpleC).append("] = ").append(simple).append("\n");
            simpleC++;
        }
        sb.append("------------------------------------------\n");
        for (SimpleData simple : list) {
            simpleDao.delete(simple);
            sb.append("deleted id ").append(simple.id).append("\n");
            Log.i(LOG_TAG, "deleting simple(" + simple.id + ")");
            simpleC++;
        }

        int createNum;
        do {
            createNum = new Random().nextInt(3) + 1;
        } while (createNum == list.size());
        for (int i = 0; i < createNum; i++) {
            // create a new simple object
            long millis = System.currentTimeMillis();
            SimpleData simple = new SimpleData(millis);
            // store it in the database
            simpleDao.create(simple);
            Log.i(LOG_TAG, "created simple(" + millis + ")");
            // output it
            sb.append("------------------------------------------\n");
            sb.append("created new entry #").append(i + 1).append(":\n");
            sb.append(simple).append("\n");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // ignore
            }
        }

        tv.setText(sb.toString());
        Log.i(LOG_TAG, "Done with page at " + System.currentTimeMillis());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //testFavorite();
    }

  /*  private void testFavorite() {

        try {
            mWFMFavoriteOffersStoreManager.saveFavoriteOfferForTheUser("1234profile", "420");
            WFMFavoriteOffersDO wfmFavoriteOffersDO = mWFMFavoriteOffersStoreManager.getFavoriteOfferForTheUser("123profile");
            if (wfmFavoriteOffersDO != null) {
                Log.d("Favorite", "wfm data=" + wfmFavoriteOffersDO.profile_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/
}