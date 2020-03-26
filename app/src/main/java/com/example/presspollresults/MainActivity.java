package com.example.presspollresults;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.math.MathUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String LOG_TAG = "MainActivity";

    private List<String> districts;

    private List<String> colombo;
    private List<String> gampaha;
    private List<String> kalutara;
    private List<String> kandy;
    private List<String> matale;
    private List<String> nuwaraeliya;
    private List<String> galle;
    private List<String> matara;
    private List<String> hambantota;
    private List<String> jaffna;
    private List<String> vanni;
    private List<String> batticaloa;
    private List<String> digamadulla;
    private List<String> trincomalee;
    private List<String> kurunegala;
    private List<String> puttalam;
    private List<String> anuradhapura;
    private List<String> polonnaruwa;
    private List<String> badulla;
    private List<String> monaragala;
    private List<String> ratnapura;
    private List<String> kegalle;

    public ArrayList<String> results;
    private ArrayList<Integer> SLPP;
    private ArrayList<Integer> UNF;
    public String final_electorate;
    public int GRtotal;
    public int SPtotal;
    public int leadingVotes;

    public ArrayList<String> electorates;
    public ArrayList<String> voteCounts;

    private TextView TopicPresPoll, TopicResults, Gota, Sajith, Votes, Gota2, Sajith2, Lead, FinalGR, FinalSP;
    private Spinner DistrictSpinner, DivisionSpinner;
    private EditText SLPPvotes, UNFvotes;
    private Button Add, ViewResults;

    public static final String EXTRA_MESSAGE = "com.example.presspollresults.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "Main Activity started");

        results = new ArrayList<String>();

        districts = new ArrayList<String>(Arrays.asList("Colombo", "Gampaha", "Kalutara", "Kandy", "Matale", "Nuwara-eliya",
                "Galle", "Matara", "Hambantota", "Jaffna", "Vanni", "Batticaloa", "Digamadulla", "Trincomalee", "Kurunegala",
                "Puttalam", "Anuradhapura", "Polonnaruwa", "Badulla", "Monaragala", "Ratnapura", "Kegalle"));

        //Electorates
        colombo = new ArrayList<String>(Arrays.asList("Col-North", "Col-Central", "Borella", "Col-East", "Col-West", "Dehiwala",
                "Ratmalana", "Kolonnawa", "Kotte", "Kaduwela", "Avissawella", "Homagama", "Maharagama", "Kesbewa", "Moratuwa", "Colombo-Postal"));
        gampaha = new ArrayList<String>(Arrays.asList("Wattala", "Negambo", "Katana", "Divulapitiya", "Meerigama", "Minuwangoda",
                "Attanagalla", "Gampaha", "Ja-Ela", "Mahara", "Dompe", "Biyagama", "Kelaniya", "Gampaha-Postal"));
        kalutara = new ArrayList<String>(Arrays.asList("Panadura", "Bandaragama", "Horana", "Bulathsinhala", "Matugama",
                "Kalutara", "Beruwala", "Agalawatta", "Kalutara-Postal"));
        kandy =  new ArrayList<String>(Arrays.asList("Galagedara", "Harispattuwa", "Pathadumbara", "Ududumbara", "Theldeniya",
                "Kumdasale", "Hewaheta", "Senkadagala", "Kandy-t", "Yatinuwara", "Udunuwara", "Gampola", "Nawalapitiya", "Kandy-Postal"));
        matale =  new ArrayList<String>(Arrays.asList("Dambulla", "Laggala", "Matale-t", "Raththota", "Matale-Postal"));
        nuwaraeliya =  new ArrayList<String>(Arrays.asList("Nuwara-eliya-t", "Kotmale", "Hanguranketha", "Walapane", "Nuwaraeliya-Postal"));
        galle =  new ArrayList<String>(Arrays.asList("Balapitiya", "Ambalangoda", "Karandeniya", "Benthara-Elpitiya", "Hiniduma",
                "Baddegama", "Rathgama", "Galle-t", "Akmeemana", "Habaraduwa", "Galle-Postal"));
        matara =  new ArrayList<String>(Arrays.asList("Deniyaya", "Hakmana", "Akuressa", "Kamburupitiya", "Devinuwara",
                "Matara-t", "Weligama", "Matara-Postal"));
        hambantota =  new ArrayList<String>(Arrays.asList("Mulkirigala", "Beliatta", "Tangalle", "Tissamaharamaya", "Hambantota-Postal"));
        jaffna =  new ArrayList<String>(Arrays.asList("Kayts", "Waddukkoddai", "Kankasanturai", "Manipay", "Kopay", "Uduppidi",
                "Point-Pedro", "Chavakachcheri", "Nallur", "Jaffna-t", "Kilinochchi", "Jaffna-Postal"));
        vanni =  new ArrayList<String>(Arrays.asList("Mannar", "Vauniya", "Mulativ", "Vanni-Postal"));
        batticaloa =  new ArrayList<String>(Arrays.asList("Kalkudah", "Batticaloa-t", "Paddiruppu", "Batticaloa-Postal"));
        digamadulla =  new ArrayList<String>(Arrays.asList("Ampara", "Samanthurai", "Kalmunai", "Pothuvil", "Digamadulla-Postal"));
        trincomalee =  new ArrayList<String>(Arrays.asList("Seruwil", "Trincomalee-t", "Mutur", "Trincomalee-Postal"));
        kurunegala =  new ArrayList<String>(Arrays.asList("Galgamuwa", "Nikaweratiya", "Yapahuwa", "Hiriyala", "Wariyapola",
                "Panduwasnuwara", "Bingiriya", "Katugampola", "Kuliyapitiya", "Dambadeniya", "Polgahawela", "Kurunegala-t",
                "Mawathagama", "Dodangaslanda", "Kurunegala-Postal"));
        puttalam =  new ArrayList<String>(Arrays.asList("Puttalam-t", "Anamaduwa", "Chillaw", "Naththandiya", "Wennapuwa", "Puttalam-Postal"));
        anuradhapura =  new ArrayList<String>(Arrays.asList("Medawachchiya", "Horowpathana", "Anuradhapura-East", "Anuradhapura-West",
                "Kalawewa", "Mihinthale", "Kekirawa", "Anuradhapura-Postal"));
        polonnaruwa =  new ArrayList<String>(Arrays.asList("Minneriya", "Medirigiriya", "Polonnaruwa-t", "Polonnaruwa-Postal"));
        badulla =  new ArrayList<String>(Arrays.asList("Mahiyanganaya", "Viyaluwa", "Passara", "Badulla-t", "Hali-ela",
                "Uva-Paranagama", "Welimada", "Bandarawela", "Haputale", "Badulla-Postal"));
        monaragala =  new ArrayList<String>(Arrays.asList("Bibile", "Monaragala-t", "Wellawaya", "Monaragala-Postal"));
        ratnapura =  new ArrayList<String>(Arrays.asList("Eheliyagoda", "Ratnapura-t", "Pelmadulla", "Balangoda", "Rakwana",
                "Niwithigala", "Kalawana", "Kolonna", "Ratnapura-Postal"));
        kegalle =  new ArrayList<String>(Arrays.asList("Dedigama", "Galigamuwa", "Kegalle-t", "Rambukkana", "Mawanella", "Aranayake",
                "Yatiyanthota", "Ruwanwella", "Deraniyagala", "Kegalle-Postal"));


        setupUIviews();


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Add button pressed");

                InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                if ((SLPPvotes.getText().toString().isEmpty()) || (UNFvotes.getText().toString().isEmpty())) {

                    final AlertDialog alert4 = new AlertDialog.Builder(MainActivity.this).create();
                    alert4.setTitle("Incomplete Entry");
                    alert4.setMessage("Please enter both votes");
                    alert4.setButton(-1, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            alert4.dismiss();
                        }
                    });
                    alert4.show();

                } else {

                    results.add(final_electorate);
                    results.add(String.valueOf(SLPPvotes.getText()));
                    results.add(String.valueOf(UNFvotes.getText()));
                    Log.i(LOG_TAG, "Results updated for " + final_electorate + " electorate");
                    Toast.makeText(MainActivity.this, "Results updated for " + final_electorate + " electorate successfully", Toast.LENGTH_LONG).show();
                    System.out.println("Results: " + results);

                    int grVotes = Integer.parseInt(SLPPvotes.getText().toString());
                    GRtotal += grVotes;
                    int spVotes = Integer.parseInt(UNFvotes.getText().toString());
                    SPtotal += spVotes;
                    leadingVotes = GRtotal - SPtotal;
                    FinalGR.setText(String.valueOf(GRtotal));
                    FinalSP.setText(String.valueOf(SPtotal));
                    Lead.setText(String.valueOf(leadingVotes));
                    //System.out.println("SLPP = " + SLPP);
                    //System.out.println("UNF = " + UNF);

                    SLPPvotes.getText().clear();
                    UNFvotes.getText().clear();

                }

            }
        });

        ViewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createSubArrays();

                Intent intent = new Intent(MainActivity.this, ImageTextListViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "ImageTextListViewActivity started");
                intent.putStringArrayListExtra("resultsUpdated", results);
                intent.putStringArrayListExtra("electorates", electorates);
                intent.putStringArrayListExtra("voteCounts", voteCounts);
                startActivity(intent);
            }
        });

    }

    /*
    private void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(results);
        editor.putString("task list", json);
    }
     */

    private void createSubArrays() {

        int len = results.size();
        System.out.println("Size of results = " + len);
        System.out.println("Results=" + results);

        electorates = new ArrayList<String>();
        voteCounts = new ArrayList<String>();

        for (int ind = 0; ind < results.size() - 2; ind = ind + 3) {
            System.out.println(results.get(ind));
            electorates.add(String.valueOf(results.get(ind)));

            String voteCount = "GR: " + results.get(ind + 1) + " | SP: " + results.get(ind + 2);
            voteCounts.add(voteCount);
        }
        Log.i(LOG_TAG, "Electorates = " + electorates);
        Log.i(LOG_TAG, "Vote Counts = " + voteCounts);
    }


    private void setupUIviews() {

        TopicPresPoll = (TextView) findViewById(R.id.tvTopicPresspoll);
        TopicResults = (TextView) findViewById(R.id.tvTopicResults);
        Votes = (TextView) findViewById(R.id.tvVotes);
        Gota = (TextView) findViewById(R.id.tvSajith2);
        Sajith = (TextView) findViewById(R.id.tvSajith2);
        Gota2 = (TextView) findViewById(R.id.tvFinalGR);
        Sajith2 = (TextView) findViewById(R.id.tvFinalSP);
        Lead = (TextView) findViewById(R.id.tvLead);
        FinalGR = (TextView) findViewById(R.id.tvFinalGR);
        FinalSP = (TextView) findViewById(R.id.tvFinalSP);

        SLPPvotes = (EditText) findViewById(R.id.etSLPPvotes);
        UNFvotes = (EditText) findViewById(R.id.etUNFvotes);

        //District Spinner
        DistrictSpinner = (Spinner) findViewById(R.id.spDistrict);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, districts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DistrictSpinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        DistrictSpinner.setOnItemSelectedListener(this);

        //Division Spinner
        DivisionSpinner = (Spinner) findViewById(R.id.spDivision);

        Add = (Button) findViewById(R.id.btnAdd);
        ViewResults = (Button) findViewById(R.id.btnView);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        final String districtSelected = districts.get(position);
        //String districtSelected = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "District: " + districtSelected, LENGTH_SHORT).show();
        System.out.println("District selected: " + districtSelected);
        //String electorate;

        if (position == 0) {
            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colombo);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = colombo.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } else if (position == 1) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gampaha);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = gampaha.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 2) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kalutara);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = kalutara.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 3) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kandy);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = kandy.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 4) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, matale);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = matale.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 5) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nuwaraeliya);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = nuwaraeliya.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 6) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, galle);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = galle.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 7) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, matara);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = matara.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 8) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hambantota);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = hambantota.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 9) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jaffna);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = jaffna.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 10) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vanni);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = vanni.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 11) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, batticaloa);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = batticaloa.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 12) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, digamadulla);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = digamadulla.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 13) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, trincomalee);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = trincomalee.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 14) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kurunegala);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = kurunegala.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 15) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, puttalam);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = puttalam.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 16) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, anuradhapura);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = anuradhapura.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 17) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, polonnaruwa);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = polonnaruwa.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 18) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, badulla);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = badulla.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 19) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monaragala);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = monaragala.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 20) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ratnapura);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = ratnapura.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (position == 21) {

            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kegalle);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DivisionSpinner.setAdapter(adapter0);
            adapter0.notifyDataSetChanged();

            DivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String electorate = kegalle.get(position);
                    final_electorate = electorate;
                    Toast.makeText(MainActivity.this, "Electorate: " + electorate, LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}