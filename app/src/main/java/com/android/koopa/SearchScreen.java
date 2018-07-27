package com.android.koopa;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class SearchScreen extends Fragment implements RadioGroup.OnCheckedChangeListener,AdapterView.OnItemSelectedListener,View.OnClickListener {
    @Nullable

    RadioGroup radioGroup;
    RadioButton radio_worker, radio_contractor;
    Spinner spinner_category, spinner_district, spinner_subdistrict;


    String radioworker,radiocontractor,spinnercategory,spinnerdistrict,spinnersubdistrict,searchbutton;

    Button search_button,backb;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_screen, container, false);
        radio_worker = view.findViewById(R.id.radio_worker);
        radio_contractor = view.findViewById(R.id.radio_contractor);
        spinner_category = view.findViewById(R.id.spinner_category);
        spinner_district = view.findViewById(R.id.spinner_district);
        spinner_subdistrict = view.findViewById(R.id.spinner_subdistrict);
        search_button = (Button) view.findViewById(R.id.search_button);
       // backb = (Button) view.findViewById(R.id.backb);
       // backb.setOnClickListener(this);



        radioGroup = (RadioGroup)view. findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);






        ArrayAdapter category = ArrayAdapter.createFromResource(this.getContext(), R.array.category_array, android.R.layout.simple_spinner_item);
        category.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner_category.setAdapter(category);
        spinner_category.setOnItemSelectedListener(this);

        ArrayAdapter district = ArrayAdapter.createFromResource(this.getContext(), R.array.district_array, android.R.layout.simple_spinner_item);
        district.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner_district.setAdapter(district);
        spinner_district.setOnItemSelectedListener(this);

        spinner_subdistrict.setOnItemSelectedListener(this);
        search_button.setOnClickListener(this);
        return view;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "selecting ");
        if (parent.getId() == R.id.spinner_category)
        {
            spinnercategory=spinner_category.getSelectedItem().toString();
        }
        if (parent.getId() == R.id.spinner_subdistrict)
        {
            spinnersubdistrict=spinner_subdistrict.getSelectedItem().toString();
        }
        if (parent.getId() == R.id.spinner_district) {


        if (spinner_district.getSelectedItem().equals("Kasargod")) {


            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_kasargod, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();

        } else if (spinner_district.getSelectedItem().equals("Kannur")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_kannur, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();

        } else if (spinner_district.getSelectedItem().equals("Kozhikode")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_kozhikode, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Wayanad")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_wayanad, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Malappuram")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_malappuram, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Idukki")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_idukki, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Palakkad")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_palakkad, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Alappuzha")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_alappuzha, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Thrissur")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_thissur, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Ernakulam")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_Ernamkulam, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Kottayam")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_kottayam, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Kollam")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_kollam, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Pathanamthitta")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_pathanamthitta, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        } else if (spinner_district.getSelectedItem().equals("Thiruvandapuram")) {

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.Thaluk_thiruvanandapuram, android.R.layout.simple_spinner_item);
            spinner_subdistrict.setAdapter(adapter2);
            spinnerdistrict=spinner_district.getSelectedItem().toString();



        }
        spinner_subdistrict.setEnabled(true);

    }

    }
    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }

 String check;

   @Override
    public void onClick(View view) {




if(radio_contractor.isChecked()==true){
    check="Contractor";
}
else {
    check="Worker";

}

        Intent i=new Intent(getActivity(),SearchResult.class);
        Bundle extras = new Bundle();
       extras.putString("check",check);
       extras.putString("spinnercategory",spinnercategory);
       extras.putString("spinnerdistrict",spinnerdistrict);
       extras.putString("spinnersubdistrict",spinnersubdistrict);
        i.putExtras(extras);

       startActivity(i);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_contractor:
                check="0";

            break;
            case R.id.radio_worker:
                check="1";

                break;

        }
    }
}
