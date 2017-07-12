package com.tallian.liveinquest.activities;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.tallian.liveinquest.*;
import com.tallian.liveinquest.engine.*;
import org.json.*;
import android.view.View.*;
import android.widget.AbsListView.*;
import java.util.*;
import android.graphics.*;
import android.text.*;
import android.graphics.drawable.*;

public class ActualListActivity extends Activity 
{
	List<Task> selectedItems = new ArrayList<>();
	Profile profile;
	Boolean multiChoice = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.actual_list);
		profile = ((Quest) getApplication()).getProfile();
		
		ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, R.layout.list_item, 
			R.id.listItemLabel, profile.getMainTasks());
		ListView actualList = (ListView) findViewById(R.id.actualList);
		actualList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		
		actualList.setMultiChoiceModeListener(new MultiChoiceModeListener() {
				@Override
				public void onItemCheckedStateChanged(ActionMode mode, int position,
													  long id, boolean checked) {
					ListView actualList = (ListView) findViewById(R.id.actualList);							  
					selectedItems.add((Task) actualList.getAdapter().getItem(position));
					View view = actualList.getAdapter().getView(position, null, actualList);
					
					// Here you can do something when items are selected/de-selected,
					// such as update the title in the CAB
				}

				@Override
				public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
					// Respond to clicks on the actions in the CAB
					switch (item.getItemId()) {
						case R.id.delete:
							deleteSelectedItems();
							mode.finish(); // Action picked, so close the CAB
							return true;
						default:
							return false;
					}
				}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					// Inflate the menu for the CAB
					MenuInflater inflater = mode.getMenuInflater();
					inflater.inflate(R.menu.item_menu, menu);
					multiChoice = true;
					return true;
				}

				@Override
				public void onDestroyActionMode(ActionMode mode) {
					ListView actualList = (ListView) findViewById(R.id.actualList);
					multiChoice = false;
					// Here you can make any necessary updates to the activity when
					// the CAB is removed. By default, selected items are deselected/unchecked.
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					// Here you can perform updates to the CAB due to
					// an invalidate() request
					return false;
				}
				
				
			});
		
		actualList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)	{
					if (multiChoice) {
						Drawable background = p2.getBackground();
						if ((background instanceof ColorDrawable) && Color.BLUE == ((ColorDrawable) background).getColor()) {
							p2.setBackgroundColor(Color.TRANSPARENT);
						} else {
							p2.setBackgroundColor(Color.BLUE);
						}
					}
				}
			});
		actualList.setAdapter(adapter);
		
    }
	
	public void onPlusButtonClick(View view)  {
        Intent intent = new Intent(this, TaskDetailActivity.class);

		startActivity(intent);
	}
	
	public boolean deleteSelectedItems() {
		
		for (Task task: selectedItems) {
			profile.getMainTasks().remove(task);
		}
		((Quest) getApplication()).saveProfile();
		return true;

	}
	
}
