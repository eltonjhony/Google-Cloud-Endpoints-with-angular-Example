package engine.app.learning.com.learningappengine;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import engine.app.learning.com.learningappengine.async.NewStoryAsync;
import engine.app.learning.com.learningappengine.helper.StoryHelper;
import engine.app.learning.com.learningappengine.model.MyApplication;

/**
 * Created by elton on 24/07/15.
 */
public class StoryActivity extends Activity {

    private StoryHelper helper;

    public StoryActivity() {
        this.helper = new StoryHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story);
        helper.init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void register(View v) {
        // feedback animation onClick()
        Animation onClickAnimation = AnimationUtils.loadAnimation(this, R.anim.onclickanim);
        v.startAnimation(onClickAnimation);
        GoogleAccountCredential credential = ((MyApplication) this.getApplication()).getGoogleAccountCredential();
        if(helper.validate(credential)) {
            new NewStoryAsync(this,credential,helper.createStoryRequestDTO()).execute();
        }
        helper.clear();
    }
}
