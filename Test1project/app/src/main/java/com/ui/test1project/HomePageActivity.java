package com.ui.test1project;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ui.test1project.model.CoursePojo;
import com.ui.test1project.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    TextView welcomeTxt, courseFeesTxt, totalFeesTxt, courseHoursTxt, totalHoursTxt;
    CheckBox accomodationCBox, insuranceCBox;
    RadioGroup radioGrp;
    Button addCourseBtn;
    Spinner courseSpinner;
    Student studentObj = null;
    String courseOpted = null;

    List<String> courseList = new ArrayList<>();

    Map<String, CoursePojo> courseMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        studentObj = new Student();
        studentObj.setGraduated(true);
        inititalizeComponents();
        loadCourseMap();
        addCourseBtn.setOnClickListener(this);

        for (String keys : courseMap.keySet()) {
            courseList.add(courseMap.get(keys).getCourseName());
        }

        //Set the contents of the spinner's adapter
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);
        courseSpinner.setOnItemSelectedListener(this);

        //CheckBoxes
        accomodationCBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int totalFees = studentObj.getTotalFees();
                if(isChecked) {
                    totalFees += 1000;
                } else {
                    totalFees -= 1000;
                }
                totalFeesTxt.setText("Total fees is " + totalFees + "$");
            }
        });
        insuranceCBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int totalFees = studentObj.getTotalFees();
                if(isChecked) {
                    totalFees += 700;
                } else {
                    totalFees -= 700;
                }
                totalFeesTxt.setText("Total fees is " + totalFees + "$");
            }
        });

        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                toggleGraduatedRadioButtons(checkedId);
            }
        });

        //Set the welcome text based on the username from the login screen
        if (getUserName() != null) {
            welcomeTxt.setText("Welcome " + getUserName());
            studentObj.setStudentName(getUserName());
        }
    }

    private String getUserName() {
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().get("userName") != null
                && !getIntent().getExtras().get("userName").toString().isEmpty()) {
            return getIntent().getExtras().get("userName").toString();
        }
        return null;
    }

    private void loadCourseMap() {
        courseMap = new HashMap<>();
        courseMap.put("Java", new CoursePojo(1, "Java", 1300, 6));
        courseMap.put("Swift", new CoursePojo(2, "Swift", 1500, 5));
        courseMap.put("iOS", new CoursePojo(3, "iOS", 1350, 5));
        courseMap.put("Android", new CoursePojo(4, "Android", 1400, 7));
        courseMap.put("Database", new CoursePojo(5, "Database", 1000, 4));
    }

    private void inititalizeComponents() {
        //Text Views
        welcomeTxt = (TextView) findViewById(R.id.welcomeTxt);
        courseFeesTxt = (TextView) findViewById(R.id.courseFeesTxt);
        totalFeesTxt = (TextView) findViewById(R.id.totalFeesTxt);
        courseHoursTxt = (TextView) findViewById(R.id.courseHoursTxt);
        totalHoursTxt = (TextView) findViewById(R.id.totalHoursTxt);
        //Spinner
        courseSpinner = (Spinner) findViewById(R.id.courseSpinner);
        //Button
        addCourseBtn = (Button) findViewById(R.id.addCourseBtn);
        //Radio group
        radioGrp = (RadioGroup) findViewById(R.id.radioGrp);
        //Checkboxes
        accomodationCBox = (CheckBox) findViewById(R.id.accomodationCBox);
        insuranceCBox = (CheckBox) findViewById(R.id.insuranceCBox);
    }

    private void toggleGraduatedRadioButtons(int checkedId) {
        if (checkedId == R.id.graduatedBtn) {
            studentObj.setGraduated(true);
        } else if (checkedId == R.id.ungraduatedBtn) {
            studentObj.setGraduated(false);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getItemAtPosition(position) != null && !parent.getItemAtPosition(position).toString().isEmpty()) {
            courseOpted = parent.getItemAtPosition(position).toString();
        }
    }

    private void addCourseAndHours(String courseOpted, int hours, int fees) {
        studentObj.getCoursesSelected().add(courseOpted);
        //Adding the total hours
        int totalHrs = studentObj.getTotalHours() + hours;
        studentObj.setTotalHours(totalHrs);
        courseHoursTxt.setText("Number of hours is " + hours);
        totalHoursTxt.setText("Total hours is " + totalHrs);

        //Adding the total fees
        int totalFees = studentObj.getTotalFees() + fees;
        studentObj.setTotalFees(fees);
        courseFeesTxt.setText("Course fees is " + fees + "$");
        totalFeesTxt.setText("Total fees is " + totalFees + "$");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        courseOpted = "Java";
    }

    @Override
    public void onClick(View v) {
        int hours = courseMap.get(courseOpted).getCourseHours();
        int fees = courseMap.get(courseOpted).getCourseFees();
        if (studentObj.getCoursesSelected() == null) {
            List<String> courseSelectedLst = new ArrayList<>();
            courseSelectedLst.add(courseOpted);
            studentObj.setCoursesSelected(courseSelectedLst);
            studentObj.setTotalHours(hours);
            addCourseAndHours(courseOpted, hours, fees);
        } else {
            if (studentObj.isGraduated() && (studentObj.getTotalHours() <= 21)) {
                addCourseAndHours(courseOpted, hours, fees);
            } else if (!studentObj.isGraduated() && (studentObj.getTotalHours() <= 19)) {
                addCourseAndHours(courseOpted, hours, fees);
            } else {
                showAlertWindow("You can't add the course");
            }
        }
    }

    public void onCheckedChanged(int checkedId) {
        int totalFees = studentObj.getTotalFees();
        if (checkedId == R.id.accomodationCBox) {
            totalFees += 1000;
        } else if (checkedId == R.id.insuranceCBox) {
            totalFees += 700;
        }
        totalFeesTxt.setText("Total fees is " + totalFees + "$");
    }

    private void showAlertWindow(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);

        // Set Alert Title
        builder.setTitle("Error");

        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }
}
