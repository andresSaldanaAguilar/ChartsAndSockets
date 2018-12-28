package e.andressaldana.navigationdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BarChartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BarChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //chart
    ArrayList <BarEntry> data;
    ArrayList<String> labels;
    BarChart chart;


    public BarChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarChartFragment newInstance(String param1, String param2) {
        BarChartFragment fragment = new BarChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_bar_chart, container, false);

        //bar chart setup
        chart = view.findViewById(R.id.barChart);
        chart.getDescription().setEnabled(false);
        BarDataSet bardataset = new BarDataSet(data, "Voltage usage on KWHr");

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int) value);
            }
        });
        chart.animateXY(2000,2000);
        BarData bardata = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.MATERIAL_COLORS);
        chart.setData(bardata);

        //spinner setup
        Spinner spinner = (Spinner) view.findViewById(R.id.general_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
        R.array.bar_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Fragment fragment = new BarChartFragment();

                ArrayList NoOfEmp = new ArrayList();
                final ArrayList<String> xLabels = new ArrayList<>();

                if(id == 0){
                    xLabels.add("Jan");
                    xLabels.add("Feb");
                    xLabels.add("Mar");
                    xLabels.add("Apr");
                    xLabels.add("May");
                    xLabels.add("Jun");
                    xLabels.add("Jul");
                    xLabels.add("Aug");
                    xLabels.add("Sep");
                    xLabels.add("Oct");
                    xLabels.add("Nov");
                    xLabels.add("Dec");
                    NoOfEmp.add("545");
                    NoOfEmp.add("640");
                    NoOfEmp.add("330");
                    NoOfEmp.add("240");
                    NoOfEmp.add("680");
                    NoOfEmp.add("787");
                    NoOfEmp.add("487");
                    NoOfEmp.add("187");
                    NoOfEmp.add("287");
                    NoOfEmp.add("487");
                    NoOfEmp.add("387");
                    NoOfEmp.add("687");
                }
                else if(id == 1){
                    xLabels.add("Mon");
                    xLabels.add("Tue");
                    xLabels.add("Wed");
                    xLabels.add("Thu");
                    xLabels.add("Fri");
                    xLabels.add("Sat");
                    xLabels.add("Sun");
                    NoOfEmp.add("145");
                    NoOfEmp.add("140");
                    NoOfEmp.add("133");
                    NoOfEmp.add("140");
                    NoOfEmp.add("70");
                    NoOfEmp.add("47");
                    NoOfEmp.add("79");
                }

                unsetChartData();
                setChartData(NoOfEmp);
                setChartLabels(xLabels);

                BarDataSet bardataset = new BarDataSet(data, "Voltage usage on KWHr");

                XAxis xAxis = chart.getXAxis();
                xAxis.setValueFormatter(new IAxisValueFormatter(){
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return labels.get((int) value);
                    }
                });
                chart.animateXY(2000,2000);
                BarData bardata = new BarData(bardataset);
                bardataset.setColors(ColorTemplate.MATERIAL_COLORS);
                chart.setData(bardata);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //this method sets the bar data
    public void setChartData(ArrayList<String> al){
        this.data = new ArrayList();
        for(int i = 0; i < al.size(); i++){
            data.add(new BarEntry((float)i,Float.parseFloat(al.get(i))));
        }
    }

    //this method unsets the bar data
    public void unsetChartData(){
        for(int i = 0; i < data.size(); i++){
            data.remove(i);
        }
    }

    //this method sets the labels on x axis
    public void setChartLabels(ArrayList<String> labels){
        this.labels = labels;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
