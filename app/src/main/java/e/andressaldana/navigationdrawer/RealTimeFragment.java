package e.andressaldana.navigationdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RealTimeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RealTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RealTimeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //chart
    LineChart chart;

    private OnFragmentInteractionListener mListener;

    public RealTimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RealTimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RealTimeFragment newInstance(String param1, String param2) {
        RealTimeFragment fragment = new RealTimeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_real_time, container, false);
        chart = view.findViewById(R.id.realTimeChart);

        LineDataSet lineDataSet = new LineDataSet(getDataSet(),"My line chart");
        lineDataSet.setDrawFilled(true);
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        LineData lineData= new LineData(lineDataSet);
        lineData.setValueFormatter(new ReportChartXAxisValueFormatter(getXAxisValues()));

        chart.setData(lineData);
        chart.animateXY(2000,2000);
        chart.invalidate();
        return view;
    }

    private List<Entry> getDataSet(){
        List<Entry> entries = new ArrayList();
        entries.add(new Entry(1,0));
        entries.add(new Entry(3,1));
        entries.add(new Entry(5,2));
        entries.add(new Entry(8,3));
        entries.add(new Entry(9,4));
        return entries;
    }

    private List<String> getXAxisValues(){
        List<String> xAxis = new ArrayList();
        xAxis.add("Jan");
        xAxis.add("Feb");
        xAxis.add("Mar");
        xAxis.add("Apr");
        xAxis.add("May");
        return xAxis;
    }

    private class ReportChartXAxisValueFormatter implements IValueFormatter {

        private List<String> labels;

        public ReportChartXAxisValueFormatter(List<String> labels){
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            try {
                int index = (int) value;
                return  this.labels.get(index);
            }catch (Exception e){
                return null;
            }
        }
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
