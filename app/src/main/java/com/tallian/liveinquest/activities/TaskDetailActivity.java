package com.tallian.liveinquest.activities;
import android.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import com.tallian.liveinquest.*;
import com.tallian.liveinquest.engine.*;
import com.tallian.liveinquest.R;
import android.widget.*;


public class TaskDetailActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details);
		
    }
	
	public void onAddButtonClick(View view) 
    {
		Profile profile = ((Quest) getApplication()).getProfile();
		EditText nameEdit = (EditText) findViewById(R.id.editTaskName);
		Task task = new Task(nameEdit.getText().toString());
        profile.getMainTasks().add(task);
		profile.getAllTasks().add(task);
		((Quest) getApplication()).saveProfile();
		
		Intent intent = new Intent(this, ActualListActivity.class);
		startActivity(intent);
	}
}
