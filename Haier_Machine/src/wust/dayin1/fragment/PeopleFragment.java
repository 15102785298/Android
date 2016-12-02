package wust.dayin1.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import wust.dayin1.adapter.TelExpandableListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haier_machine.R;

/**
 * ѧϰ�ķɵ�˼·���ǳ���������
 * @author BanXia
 */
public class PeopleFragment extends Fragment{
	View view;
	private TelExpandableListAdapter adapter;
	private ExpandableListView telEALV;
    private List<String> listDataHeader;
    private Map<String, List<String>> listDataChild;   
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_function, container , false) ;	
		findView() ;//������˼·
		addListener() ;//���ú�ѭ��
		return view ;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		loadData();
	};
	
	/**
	 * �󶨿ؼ�
	 */
	private void findView(){			
		telEALV=(ExpandableListView)view.findViewById(R.id.function_edlv);
		listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        adapter=new TelExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        telEALV.setAdapter(adapter);
	}
	
	/**
	 * ����������
	 */
	private void addListener(){
	
		// ��Ԫ�ص�����Ӧ
        telEALV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), "�����ڴ�:"+ listDataHeader.get(groupPosition) +":" +listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_LONG).show();
                return false;
            }
        });
	}
	
	/**
	 * ��xml�ļ��м��ص绰����
	 */
	private void loadData() {
        //��xml��ȡ�����������
        String[] telGroupNameArr = getResources().getStringArray(R.array.string_people);

        ArrayList<String[]> arr = new ArrayList<String[]>() ;
        arr.add(getResources().getStringArray(R.array.telGroupItemArr00)) ;
        arr.add(getResources().getStringArray(R.array.telGroupItemArr11)) ;
        arr.add(getResources().getStringArray(R.array.telGroupItemArr22)) ;
        arr.add(getResources().getStringArray(R.array.telGroupItemArr33)) ;
        arr.add(getResources().getStringArray(R.array.telGroupItemArr44)) ;
        arr.add(getResources().getStringArray(R.array.telGroupItemArr55)) ;
        arr.add(getResources().getStringArray(R.array.telGroupItemArr66)) ;
        
        listDataHeader.clear() ;
        listDataChild.clear() ;
        for(int i = 0 ; i < telGroupNameArr.length ; i ++){
        	// ���������������
            List<String> telItemArr = new ArrayList<String>();
            for(int j = 0 ; j < arr.get(i).length ; j ++)
            	telItemArr.add(arr.get(i)[j]) ;
            
            listDataHeader.add(telGroupNameArr[i]) ;
        	listDataChild.put(telGroupNameArr[i], telItemArr);
        }
        
        adapter.notifyDataSetChanged() ;
    }
	
}
