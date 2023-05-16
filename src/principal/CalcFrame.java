package principal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;

public class CalcFrame extends JFrame {
  private BigDecimal op1;
  private BigDecimal op2;
  private int contaceros = 0;
  private BigDecimal valcoma;
  private boolean entrado = false;
  private boolean negaplicado = false;
  private boolean coma = false;
  BigDecimal result = new BigDecimal(0);
  private boolean cur1 = true;
  private boolean negativ = false;
  private String operator;
  private JTextField pantalla;
  public void operar() {
    if (operator.equals("+")) {
      result = op1.add(op2);
    }
    if (operator.equals("-")) {
      result = op1.subtract(op2);
    }
    if (operator.equals("*")) {
      result = op1.multiply(op2);
    }
    if (operator.equals("/")) {

      result = (op1.divide(op2, 2, RoundingMode.HALF_UP));
    }
    cur1 = true;
    MathContext m = new MathContext(3);
    result = result.setScale(2, RoundingMode.DOWN);
    String resultado = result.toString();
    pantalla.setText(resultado);
    op2 = null;
    op1 = result;
    result = new BigDecimal(resultado);
    negativ = false;
    entrado = true;
    coma = false;
    operator = null;
    valcoma = null;
    contaceros = 0;
  }
  public void pulsarNum(String num) {
    pantalla.setText(pantalla.getText() + num);
    if (cur1) {
      String valor = "";
      String valorcoma = "";
      if (entrado) {
        pantalla.setText(num);
        op1 = new BigDecimal(num);
        entrado = false;
      } else {
        if (op1 == null) {
          if (coma) {
            contaceros++;
            if (valcoma == null) {
              valcoma = new BigDecimal(num);

            } else {
              valorcoma = valcoma.toString() + num;
              valcoma = new BigDecimal(valorcoma);

            }

          } else {
            valor = num;
          }
        } else {
          if (coma) {
            contaceros++;
            if (valcoma == null) {
              valcoma = new BigDecimal(num);

            } else {
              valorcoma = valcoma.toString() + num;
              valcoma = new BigDecimal(valorcoma);

            }
          } else {
            valor = op1.toString() + num;

          }

        }
        if (!coma) {
          op1 = new BigDecimal(valor);
        }
      }

    } else {
      String valor = "";
      String valorcoma = "";
      if (entrado) {
        pantalla.setText(num);
        op1 = new BigDecimal(num);
        entrado = false;
      } else {
        if (op2 == null) {
          if (coma) {
            contaceros++;
            if (valcoma == null) {
              valcoma = new BigDecimal(num);

            } else {
              valorcoma = valcoma.toString() + num;
              valcoma = new BigDecimal(valorcoma);

            }

          } else {
            valor = num;
          }
        } else {
          if (coma) {
            contaceros++;
            if (valcoma == null) {
              valcoma = new BigDecimal(num);

            } else {
              valorcoma = valcoma.toString() + num;
              valcoma = new BigDecimal(valorcoma);

            }
          } else {
            valor = op2.toString() + num;
          }

        }
        if (!coma) {
          op2 = new BigDecimal(valor);
        }
      }

    }
  }
  public CalcFrame() {
    super();
    getContentPane().setBackground(new Color(163, 183, 220));
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    setVisible(true);
    setLocation(400, 100);
    setSize(413, 573);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setTitle("JORG");
    getContentPane().setLayout(null);
    pantalla = new JTextField();
    pantalla.setEditable(false);
    pantalla.setFont(new Font("Tahoma", Font.PLAIN, 34));
    pantalla.setBounds(10, 11, 377, 87);
    getContentPane().add(pantalla);
    pantalla.setColumns(10);

    JButton sumarButton = new JButton("+");
    sumarButton.setBackground(new Color(255, 159, 162));
    sumarButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!cur1 && op2 != null) {
          if (negativ) {
            op2 = op2.negate();
          }
          if (coma && valcoma != null) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 3, RoundingMode.HALF_UP);
            op2 = op2.add(valcoma);
          }
          operar();
          cur1 = false;
          entrado = false;
          operator = "+";
          coma = false;
          valcoma = null;
          pantalla.setText(pantalla.getText() + " + ");

          op1 = result;

          op2 = null;
        }
        if (operator != null) {
          operator = "+";
          pantalla.setText(op1 + " + ");
        }
        if (cur1 && op1 != null && !pantalla.getText().equals("0,")) {

          cur1 = false;
          operator = "+";
          pantalla.setText(pantalla.getText() + " + ");
          entrado = false;

          if (coma && valcoma != null) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 2, RoundingMode.HALF_UP);
            op1 = op1.add(valcoma);
            coma = false;
            valcoma = null;
          }

          if (negativ) {
            op1 = op1.negate();
            negativ = false;
          }
        }

      }
    });
    sumarButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
    sumarButton.setBounds(296, 373, 91, 65);
    getContentPane().add(sumarButton);

    JButton restarButton = new JButton("-");
    restarButton.setBackground(new Color(255, 159, 162));
    restarButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!cur1 && op2 != null) {
          if (negativ) {
            op2 = op2.negate();
          }
          if (coma && valcoma != null) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 3, RoundingMode.HALF_UP);
            op2 = op2.add(valcoma);
          }
          operar();
          cur1 = false;
          entrado = false;
          operator = "-";
          coma = false;
          valcoma = null;
          pantalla.setText(pantalla.getText() + " - ");

          op1 = result;
          op2 = null;
        }

        if (operator != null) {
          operator = "-";
          pantalla.setText(op1 + " - ");
        }
        if (cur1 && op1 != null && !pantalla.getText().equals("0,")) {
          cur1 = false;
          operator = "-";
          pantalla.setText(pantalla.getText() + " - ");
          entrado = false;

          if (coma && valcoma != null) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 3, RoundingMode.HALF_UP);
            op1 = op1.add(valcoma);
            coma = false;
            valcoma = null;
          }

          if (negativ) {
            op1 = op1.negate();
            negativ = false;
          }
        }

      }
    });
    restarButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
    restarButton.setBounds(296, 285, 91, 65);
    getContentPane().add(restarButton);

    JButton dividirButton = new JButton("\u00F7");
    dividirButton.setBackground(new Color(255, 159, 162));
    dividirButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!cur1 && op2 != null) {
          if (negativ) {
            op2 = op2.negate();
          }
          if (coma && valcoma != null) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 3, RoundingMode.HALF_UP);
            op2 = op2.add(valcoma);
          }
          operar();
          cur1 = false;
          entrado = false;
          operator = "/";
          coma = false;
          valcoma = null;
          pantalla.setText(pantalla.getText() + "\u00F7 ");

          op1 = result;
          op2 = null;
        }
        if (operator != null) {
          operator = "/";
          pantalla.setText(op1 + " \u00F7 ");
        }
        if (cur1 && op1 != null && !pantalla.getText().equals("0,")) {
          cur1 = false;
          operator = "/";
          pantalla.setText(pantalla.getText() + " \u00F7 ");
          entrado = false;

          if (coma && valcoma != null) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 3, RoundingMode.HALF_UP);
            op1 = op1.add(valcoma);
            coma = false;
            valcoma = null;
          }

          if (negativ) {
            op1 = op1.negate();
            negativ = false;
          }
        }

      }
    });
    dividirButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
    dividirButton.setBounds(296, 109, 91, 65);
    getContentPane().add(dividirButton);

    JButton multiplicarButton = new JButton("X");
    multiplicarButton.setForeground(new Color(0, 0, 0));
    multiplicarButton.setBackground(new Color(255, 159, 162));
    multiplicarButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!cur1 && op2 != null) {
          if (negativ) {
            op2 = op2.negate();
          }
          if (coma && valcoma != null) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 3, RoundingMode.HALF_UP);
            op2 = op2.add(valcoma);
          }

          operar();
          cur1 = false;
          entrado = false;
          operator = "*";

          pantalla.setText(pantalla.getText() + " X ");
          coma = false;
          valcoma = null;
          op1 = result;
          op2 = null;
        }
        if (operator != null) {
          operator = "*";
          pantalla.setText(op1 + " X ");
        }
        if (cur1 && op1 != null && !pantalla.getText().equals("0,")) {
          cur1 = false;
          operator = "*";
          pantalla.setText(pantalla.getText() + " X ");
          entrado = false;

          if (coma && valcoma != null) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 3, RoundingMode.HALF_UP);
            op1 = op1.add(valcoma);
            coma = false;
            valcoma = null;
          }

          if (negativ) {
            op1 = op1.negate();
            negativ = false;
          }
        }

      }
    });
    multiplicarButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
    multiplicarButton.setBounds(296, 196, 91, 65);
    getContentPane().add(multiplicarButton);

    JButton btn_7 = new JButton("7");
    btn_7.setBackground(new Color(211, 211, 211));
    btn_7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("7");
      }
    });
    btn_7.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_7.setBounds(10, 113, 80, 58);
    getContentPane().add(btn_7);

    JButton brn_8 = new JButton("8");
    brn_8.setBackground(new Color(211, 211, 211));
    brn_8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("8");
      }
    });
    brn_8.setFont(new Font("Tahoma", Font.PLAIN, 34));
    brn_8.setBounds(100, 113, 80, 58);
    getContentPane().add(brn_8);

    JButton btn_9 = new JButton("9");
    btn_9.setBackground(new Color(211, 211, 211));
    btn_9.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("9");
      }
    });
    btn_9.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_9.setBounds(195, 113, 80, 58);
    getContentPane().add(btn_9);

    JButton btn_4 = new JButton("4");
    btn_4.setBackground(new Color(211, 211, 211));
    btn_4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("4");
      }
    });
    btn_4.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_4.setBounds(10, 203, 80, 58);
    getContentPane().add(btn_4);

    JButton btn_5 = new JButton("5");
    btn_5.setBackground(new Color(211, 211, 211));
    btn_5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("5");
      }
    });
    btn_5.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_5.setBounds(100, 203, 80, 58);
    getContentPane().add(btn_5);

    JButton btn_6 = new JButton("6");
    btn_6.setBackground(new Color(211, 211, 211));
    btn_6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("6");
      }
    });
    btn_6.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_6.setBounds(195, 203, 80, 58);
    getContentPane().add(btn_6);

    JButton btn_1 = new JButton("1");
    btn_1.setBackground(new Color(211, 211, 211));
    btn_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("1");
      }
    });
    btn_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_1.setBounds(10, 292, 80, 58);
    getContentPane().add(btn_1);

    JButton btn_2 = new JButton("2");
    btn_2.setBackground(new Color(211, 211, 211));
    btn_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("2");

      }
    });
    btn_2.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_2.setBounds(100, 292, 80, 58);
    getContentPane().add(btn_2);

    JButton btn_3 = new JButton("3");
    btn_3.setBackground(new Color(211, 211, 211));
    btn_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pulsarNum("3");
      }
    });
    btn_3.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_3.setBounds(195, 292, 80, 58);
    getContentPane().add(btn_3);

    JButton signo_btn = new JButton("\u00B1");
    signo_btn.setBackground(new Color(255, 159, 162));
    signo_btn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!negativ) {
          if ((cur1 && op1 == null) || (!cur1 && op2 == null)) {
            if (negativ) {
              negativ = false;
            } else {
              pantalla.setText(pantalla.getText() + "-");
              negativ = true;
            }
            negaplicado = true;
          }

        }

      }
    });
    signo_btn.setFont(new Font("Tahoma", Font.PLAIN, 34));
    signo_btn.setBounds(10, 380, 80, 58);
    getContentPane().add(signo_btn);

    JButton btn_0 = new JButton("0");
    btn_0.setBackground(new Color(211, 211, 211));
    btn_0.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!pantalla.getText().equals("") || coma) {
          pantalla.setText(pantalla.getText() + "0");
          if (coma) {
            if (valcoma != null) {
              if (valcoma.equals(0)) {
                contaceros++;

              }
            } else {
              contaceros++;

            }

          }
          if (cur1) {
            String valor = "";
            if (entrado) {
              pantalla.setText("0");
              op1 = new BigDecimal("0");
              entrado = false;
            } else {
              if (op1 == null) {
                if (coma) {
                  if (valcoma == null) {
                    valcoma = new BigDecimal("0");
                  } else {
                    valor = valcoma.toString() + "0";
                  }

                } else {
                  valor = "0";
                }
              } else {
                if (coma) {
                  if (valcoma == null) {
                    valcoma = new BigDecimal("0");
                  }
                } else {
                  valor = op1.toString() + "0";
                }

              }
              if (!coma) {
                op1 = new BigDecimal(valor);
              }
            }

          } else {
            String valor = "";
            if (entrado) {
              pantalla.setText("0");
              op1 = new BigDecimal("0");
              entrado = false;
            } else {
              if (op2 == null) {
                if (coma) {
                  if (valcoma == null) {
                    valcoma = new BigDecimal("0");
                  } else {
                    valor = valcoma.toString() + "0";
                  }

                } else {
                  valor = "0";
                }
              } else {
                if (coma) {
                  if (valcoma == null) {
                    valcoma = new BigDecimal("0");
                  }
                } else {
                  valor = op2.toString() + "0";
                }

              }
              if (!coma) {
                op2 = new BigDecimal(valor);
              }
            }

          }
        }
      }
    });
    btn_0.setFont(new Font("Tahoma", Font.PLAIN, 34));
    btn_0.setBounds(100, 380, 80, 58);
    getContentPane().add(btn_0);

    JButton coma_btn = new JButton(",");
    coma_btn.setBackground(new Color(255, 159, 162));
    coma_btn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (entrado == true && coma == false && op2 == null) {
          pantalla.setText("0,");
          op1 = new BigDecimal(0);
          valcoma = new BigDecimal(0);
          coma = true;
          entrado = false;
        }
        if (entrado == false && coma == false) {
          if (cur1) {
            if (op1 == null) {
              pantalla.setText("0");
              coma = true;
              op1 = new BigDecimal(0);
            }
            if (op1 != null) {
              pantalla.setText(pantalla.getText() + ",");
            }
          } else {
            if (op2 == null) {
              pantalla.setText(pantalla.getText() + "0");
              coma = true;
              op2 = new BigDecimal(0);
            }
            if (op2 != null) {
              pantalla.setText(pantalla.getText() + ",");
            }
          }
          coma = true;
        }

      }
    });
    coma_btn.setFont(new Font("Tahoma", Font.PLAIN, 34));
    coma_btn.setBounds(195, 380, 80, 58);
    getContentPane().add(coma_btn);

    JButton enter_btn = new JButton("=");
    enter_btn.setBackground(new Color(255, 159, 162));
    enter_btn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (!cur1 && op2 != null) {
          if (negativ) {
            op2 = op2.negate();
          }
          if (coma) {
            valcoma = valcoma.divide(new BigDecimal(Math.pow(10, contaceros)), 3, RoundingMode.HALF_UP);

            op2 = op2.add(valcoma);
          }
          //try {

          operar();

          //} catch (Exception err) {
          //pantalla.setText("ERROR");
          //}
        }

      }
    });
    enter_btn.setFont(new Font("Tahoma", Font.PLAIN, 34));
    enter_btn.setBounds(195, 458, 192, 65);
    getContentPane().add(enter_btn);

    JButton clear_btn = new JButton("CLEAR");
    clear_btn.setBackground(new Color(255, 159, 162));
    clear_btn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        coma = false;
        entrado = false;
        valcoma = null;
        negaplicado = false;
        negativ = false;
        cur1 = true;
        op1 = null;
        contaceros = 0;
        op2 = null;
        operator = null;
        cur1 = true;
        result = new BigDecimal(0);
        pantalla.setText("");
      }
    });
    clear_btn.setFont(new Font("Tahoma", Font.PLAIN, 34));
    clear_btn.setBounds(10, 458, 170, 65);
    getContentPane().add(clear_btn);

    repaint();
    revalidate();

  }
}