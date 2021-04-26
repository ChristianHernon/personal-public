package com.example.christian.mathcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    private Math myMath = new Math();
    private Button btnZero;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnAdd;
    private Button btnSubtract;
    private Button btnMultiply;
    private Button btnDivide;
    private Button btnEquals;
    private Button btnDelete;
    private Button btnClear;
    private Button btnClearEverything;
    private Button btnNegative;
    private Button btnDecimal;
    private TextView tvOutput;
    private String theMath = "";
    private double num1 = 0;
    private double num2 = 0;
    private boolean num1neg = false;
    private boolean num2neg = false;
    private String operator = "";
    private String result = "";
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect XML to Java
        btnZero = (Button) findViewById(R.id.btnZero);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnEquals = (Button) findViewById(R.id.btnEquals);
        btnDelete = (Button) findViewById(R.id.btnDel);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClearEverything = (Button) findViewById(R.id.btnClearEverything);
        btnNegative = (Button) findViewById(R.id.btnNegative);
        btnDecimal = (Button) findViewById(R.id.btnDecimal);
        tvOutput = (TextView) findViewById(R.id.tvOutput);
        tvResult = (TextView) findViewById(R.id.tvResult);

        // unable to make a default string that is empty so clear text here
        tvResult.setText("");

        //region "Listeners"
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "0";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "0";
                    updateDisplay();
                }
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "1";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "1";
                    updateDisplay();
                }
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "2";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "2";
                    updateDisplay();
                }
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "3";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "3";
                    updateDisplay();
                }
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "4";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "4";
                    updateDisplay();
                }
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "5";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "5";
                    updateDisplay();
                }
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "6";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "6";
                    updateDisplay();
                }
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "7";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "7";
                    updateDisplay();
                }
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "8";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "8";
                    updateDisplay();
                }
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1neg) {
                    theMath += "9";
                    updateDisplay();
                } else if (!operator.isEmpty() && !num2neg) {
                    theMath += "9";
                    updateDisplay();
                }
            }
        });

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator.isEmpty() && !theMath.contains(".")) {
                    theMath += ".";
                    updateDisplay();
                } else if (!operator.isEmpty()) {
                    String[] numbers = theMath.split("\\" + operator);
                    if (numbers.length == 1) {
                        theMath += ".";
                        updateDisplay();
                    } else if (!numbers[1].contains(".")) {
                        theMath += ".";
                        updateDisplay();
                    }
                }
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass string to function to retrieve the numbers and operator
                checkString();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an operator already is assigned
                if (operator.isEmpty() && !theMath.isEmpty()) {
                    operator = "+";
                    theMath += "+";
                    updateDisplay();
                } else if (operator.isEmpty() && theMath.isEmpty() && !result.isEmpty()) {
                    theMath = result;
                    operator = "+";
                    theMath += operator;
                    updateDisplay();
                } else if (theMath.isEmpty()) {
                } else {
                    // TODO if operator already exists do rolling add
                    checkString();
                    theMath = String.valueOf(tvResult.getText());
                    theMath = theMath.substring(1, theMath.length());
                    operator = "+";
                    theMath += "+";
                    updateDisplay();
                    tvResult.setText("");
                }
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator.isEmpty() && !theMath.isEmpty()) {
                    operator = "-";
                    theMath += "-";
                    updateDisplay();
                } else if (operator.isEmpty() && theMath.isEmpty() && !result.isEmpty()) {
                    theMath = result;
                    operator = "-";
                    theMath += operator;
                    updateDisplay();
                } else if (theMath.isEmpty()) {
                    // do nothing
                } else {
                    // TODO rolling subtract
                    checkString();
                    theMath = String.valueOf(tvResult.getText());
                    theMath = theMath.substring(1, theMath.length());
                    operator = "-";
                    theMath += "-";
                    updateDisplay();
                    tvResult.setText("");
                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator.isEmpty() && !theMath.isEmpty()) {
                    operator = "÷";
                    theMath += "÷";
                    updateDisplay();
                } else if (operator.isEmpty() && theMath.isEmpty() && !result.isEmpty()) {
                    theMath = result;
                    operator = "÷";
                    theMath += operator;
                    updateDisplay();
                } else if (theMath.isEmpty()) {
                    //do nothing
                } else {
                    // TODO rolling subtract
                    checkString();
                    theMath = String.valueOf(tvResult.getText());
                    theMath = theMath.substring(1, theMath.length());
                    operator = "÷";
                    theMath += "÷";
                    updateDisplay();
                    tvResult.setText("");
                }
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator.isEmpty() && !theMath.isEmpty()) {
                    operator = "×";
                    theMath += "×";
                    updateDisplay();
                } else if (operator.isEmpty() && theMath.isEmpty() && !result.isEmpty()) {
                    theMath = result;
                    operator = "×";
                    theMath += operator;
                    updateDisplay();
                } else if (theMath.isEmpty()) {
                    // do nothing
                } else {
                    // TODO rolling subtract
                    checkString();
                    theMath = String.valueOf(tvResult.getText());
                    theMath = theMath.substring(1, theMath.length());
                    operator = "×";
                    theMath += "×";
                    updateDisplay();
                    tvResult.setText("");
                }
            }
        });

        btnClearEverything.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEverything();
                updateDisplay();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (theMath.length() > 0) {
                    if (operator.isEmpty()) {
                        theMath = theMath.substring(0, theMath.length() - 1);
                        updateDisplay();
                    } else if (theMath.contains(operator)) {
                        String temp = theMath.substring(0, theMath.length() - 1);
                        if (temp.contains("\\" + operator)) {
                            theMath = temp;
                            updateDisplay();
                        } else {
                            operator = "";
                            theMath = temp;
                            updateDisplay();
                        }
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theMath = "";
                operator = "";
                num1neg = false;
                num2neg = false;
                updateDisplay();
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!theMath.isEmpty() && operator.isEmpty()) {
                    num1neg = true;
                    theMath = "-" + theMath;
                    updateDisplay();
                } else {
                    String[] temp = theMath.split("\\" + operator);
                    if (temp.length > 1) {
                        num2neg = true;
                        temp[1] = "-" + temp[1];
                        theMath = temp[0] + operator + temp[1];
                        updateDisplay();
                    }
                }
            }
        });
        //endregion
    }// end onCreate

    // Update display as new values are keyed in
    public void updateDisplay() {
        tvOutput.setText(theMath);
    }

    // Clear all values and displays
    public void clearEverything() {
        tvResult.setText("");
        theMath = "";
        operator = "";
        num1 = 0;
        num2 = 0;
        num1neg = false;
        num2neg = false;
    }

    // Check the string before passing it to the Math class
    public void checkString() {
        if (!operator.isEmpty()) {
            String[] numbers = theMath.split("\\" + operator);
            if (numbers.length == 2) {
                num1 = Double.parseDouble(numbers[0]);
                num2 = Double.parseDouble(numbers[1]);
                result = String.valueOf(myMath.callMathOperation(operator, num1, num2));
                tvResult.setText("=" + result);
                theMath = "";
                operator = "";
            }
        }
    }// end checkString

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
