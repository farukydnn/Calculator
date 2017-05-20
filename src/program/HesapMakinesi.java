package program;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Insets;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.awt.event.ActionEvent;

public class HesapMakinesi {

	private JFrame frmHesapMakinesi;	
	private JTextField txt_Sonuc;
	private JTextField txt_IslemGecmisi;
	private JTextField txt_Memory;
	
	private boolean esittirTiklandi = false;
	private boolean sayiGirildi = false;
	private boolean parantezGirildi = false;
	private boolean isaretDegisti = false;
	private boolean virgulKoyuldu = false;
	private boolean eksiKoyulabilir = false;
	private boolean hafizayaKaydedildi = false;

	private BigDecimal islemSonucu = new BigDecimal("0");
	private BigDecimal hafizaSayi = new BigDecimal("0");
	
	private DecimalFormat sonucFormat = new DecimalFormat();

	private String girilenSayi = "0";
	private String siradakiIslem = "";
	private String islemGecmisi = "";
	private String islemGecmisiTemp = "";
	private String sonucString = "";

	private Font sonucFont;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HesapMakinesi window = new HesapMakinesi();
					window.frmHesapMakinesi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HesapMakinesi() {
		initialize();		

		sonucFormat.setMinimumFractionDigits(0);
		sonucFormat.setMaximumFractionDigits(16);
		sonucFormat.setGroupingUsed(false);

		DecimalFormatSymbols ciktiSembolu = new DecimalFormatSymbols();
		ciktiSembolu.setDecimalSeparator('.');

		sonucFormat.setDecimalFormatSymbols(ciktiSembolu);
		
		sonucFont = txt_Sonuc.getFont();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHesapMakinesi = new JFrame();
		frmHesapMakinesi.setTitle("Hesap Makinesi");
		frmHesapMakinesi.getContentPane().setBackground(Color.DARK_GRAY);
		frmHesapMakinesi.getContentPane().setLayout(null);

		JPanel pnl_Tuslar = new JPanel();
		pnl_Tuslar.setBackground(Color.DARK_GRAY);
		pnl_Tuslar.setBounds(11, 83, 222, 178);
		frmHesapMakinesi.getContentPane().add(pnl_Tuslar);
		pnl_Tuslar.setLayout(null);

		JButton btn_0 = new JButton("0");
		btn_0.setName("0");
		btn_0.setMargin(new Insets(0, 0, 0, 0));
		btn_0.setBounds(2, 148, 86, 26);
		btn_0.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_0);

		JButton btn_1 = new JButton("1");
		btn_1.setName("1");
		btn_1.setMargin(new Insets(0, 0, 0, 0));
		btn_1.setBounds(2, 119, 42, 26);
		btn_1.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_1);

		JButton btn_2 = new JButton("2");
		btn_2.setName("2");
		btn_2.setMargin(new Insets(0, 0, 0, 0));
		btn_2.setBounds(46, 119, 42, 26);
		btn_2.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_2);

		JButton btn_3 = new JButton("3");
		btn_3.setName("3");
		btn_3.setMargin(new Insets(0, 0, 0, 0));
		btn_3.setBounds(90, 119, 42, 26);
		btn_3.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_3);

		JButton btn_4 = new JButton("4");
		btn_4.setName("4");
		btn_4.setMargin(new Insets(0, 0, 0, 0));
		btn_4.setBounds(2, 90, 42, 26);
		btn_4.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_4);

		JButton btn_5 = new JButton("5");
		btn_5.setName("5");
		btn_5.setMargin(new Insets(0, 0, 0, 0));
		btn_5.setBounds(46, 90, 42, 26);
		btn_5.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_5);

		JButton btn_6 = new JButton("6");
		btn_6.setName("6");
		btn_6.setMargin(new Insets(0, 0, 0, 0));
		btn_6.setBounds(90, 90, 42, 26);
		btn_6.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_6);

		JButton btn_7 = new JButton("7");
		btn_7.setName("7");
		btn_7.setMargin(new Insets(0, 0, 0, 0));
		btn_7.setBounds(2, 61, 42, 26);
		btn_7.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_7);

		JButton btn_8 = new JButton("8");
		btn_8.setName("8");
		btn_8.setMargin(new Insets(0, 0, 0, 0));
		btn_8.setBounds(46, 61, 42, 26);
		btn_8.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_8);

		JButton btn_9 = new JButton("9");
		btn_9.setName("9");
		btn_9.setMargin(new Insets(0, 0, 0, 0));
		btn_9.setBounds(90, 61, 42, 26);
		btn_9.addActionListener(new numberAction());
		pnl_Tuslar.add(btn_9);

		JButton btn_Arti = new JButton("+");
		btn_Arti.setName("Topla");
		btn_Arti.addActionListener(new operatorAction());
		btn_Arti.setMargin(new Insets(0, 0, 0, 0));
		btn_Arti.setBounds(134, 148, 42, 26);
		pnl_Tuslar.add(btn_Arti);

		JButton button_Eksi = new JButton("-");
		button_Eksi.setName("Cikar");
		button_Eksi.addActionListener(new operatorAction());
		button_Eksi.setMargin(new Insets(0, 0, 0, 0));
		button_Eksi.setBounds(134, 119, 42, 26);
		pnl_Tuslar.add(button_Eksi);

		JButton btn_Carp = new JButton("*");
		btn_Carp.setName("Carp");
		btn_Carp.addActionListener(new operatorAction());
		btn_Carp.setMargin(new Insets(0, 0, 0, 0));
		btn_Carp.setBounds(134, 90, 42, 26);
		pnl_Tuslar.add(btn_Carp);

		JButton btn_Bol = new JButton("/");
		btn_Bol.setName("Bol");
		btn_Bol.addActionListener(new operatorAction());
		btn_Bol.setMargin(new Insets(0, 0, 0, 0));
		btn_Bol.setBounds(134, 61, 42, 26);
		pnl_Tuslar.add(btn_Bol);

		JButton btn_Mod = new JButton("mod");
		btn_Mod.setName("ModAl");
		btn_Mod.addActionListener(new operatorAction());
		btn_Mod.setMargin(new Insets(0, 0, 0, 0));
		btn_Mod.setBounds(178, 61, 42, 26);
		pnl_Tuslar.add(btn_Mod);

		JButton btn_MemoryClear = new JButton("MC");
		btn_MemoryClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt_Memory.setText("");
				hafizaSayi = BigDecimal.ZERO;
				
			}
		});
		btn_MemoryClear.setMargin(new Insets(0, 0, 0, 0));
		btn_MemoryClear.setBounds(2, 3, 42, 26);
		pnl_Tuslar.add(btn_MemoryClear);

		JButton btn_MemoryRead = new JButton("MR");
		btn_MemoryRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (hafizaSayi.compareTo(BigDecimal.ZERO) != 0) {			
					isaretDegisti = false;
					sayiGirildi = true;
					virgulKoyuldu = false;
					eksiKoyulabilir = false;
					hafizayaKaydedildi = true;

					if (parantezGirildi) {
						parantezGirildi = false;
						islemGecmisiTemp = "";
						SonucYazdir(txt_IslemGecmisi, islemGecmisi);
					}
					
					if (esittirTiklandi) {
						islemSonucu = hafizaSayi;
						SonucYazdir(txt_Sonuc, sonucFormat.format(islemSonucu));

					} else {		
						girilenSayi = sonucFormat.format(hafizaSayi);
						SonucYazdir(txt_Sonuc, girilenSayi);
					}
				}

			}		
		});
		btn_MemoryRead.setMargin(new Insets(0, 0, 0, 0));
		btn_MemoryRead.setBounds(46, 3, 42, 26);
		pnl_Tuslar.add(btn_MemoryRead);

		JButton btn_MemorySet = new JButton("MS");
		btn_MemorySet.setName("hafEsitle");
		btn_MemorySet.addActionListener(new MemoryAction());
		btn_MemorySet.setMargin(new Insets(0, 0, 0, 0));
		btn_MemorySet.setBounds(90, 3, 42, 26);
		pnl_Tuslar.add(btn_MemorySet);

		JButton btn_MemoryAdd = new JButton("M+");
		btn_MemoryAdd.setName("hafEkle");
		btn_MemoryAdd.addActionListener(new MemoryAction());
		btn_MemoryAdd.setMargin(new Insets(0, 0, 0, 0));
		btn_MemoryAdd.setBounds(134, 3, 42, 26);
		pnl_Tuslar.add(btn_MemoryAdd);

		JButton btn_MemorySubtract = new JButton("M-");
		btn_MemorySubtract.setName("hafCikar");
		btn_MemorySubtract.addActionListener(new MemoryAction());
		btn_MemorySubtract.setMargin(new Insets(0, 0, 0, 0));
		btn_MemorySubtract.setBounds(178, 3, 42, 26);
		pnl_Tuslar.add(btn_MemorySubtract);

		JButton b_BireBol = new JButton("1/x");
		b_BireBol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ParantezHazirla();

				if (esittirTiklandi) {

					try {
						islemSonucu = BigDecimal.ONE.divide(islemSonucu, 16, RoundingMode.HALF_UP);

					} catch (ArithmeticException bolumHatasi) {
						islemSonucu = BigDecimal.ZERO;
					}

					SonucYazdir(txt_Sonuc, sonucFormat.format(islemSonucu));

				} else {

					try {	

						if (sayiGirildi) {
							BigDecimal tempSayi = new BigDecimal(girilenSayi);
							girilenSayi = sonucFormat.format(BigDecimal.ONE.divide(tempSayi, 16, RoundingMode.HALF_UP));

						} else {
							sayiGirildi = true;

							girilenSayi = sonucFormat.format(BigDecimal.ONE.divide(islemSonucu, 16, RoundingMode.HALF_UP));
						}

					} catch (ArithmeticException bolumHatasi) {
						girilenSayi = "0";
					}

					SonucYazdir(txt_Sonuc, girilenSayi);
				}

				islemGecmisiTemp = "rec(" + islemGecmisiTemp + ")";

				SonucYazdir(txt_IslemGecmisi, islemGecmisi + " " + islemGecmisiTemp);

				eksiKoyulabilir = false;

			}

		});
		b_BireBol.setMargin(new Insets(0, 0, 0, 0));
		b_BireBol.setBounds(178, 90, 42, 26);
		pnl_Tuslar.add(b_BireBol);	

		JButton btn_IsaretCevir = new JButton("\u00B1");
		btn_IsaretCevir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!sayiGirildi || hafizayaKaydedildi)
					ParantezHazirla();

				if (esittirTiklandi) {

					if (!sonucString.isEmpty()) {			
						BigDecimal tempSayi = new BigDecimal(sonucString);

						if (tempSayi.compareTo(BigDecimal.ZERO) != 0) {
							islemSonucu = tempSayi.negate();
							sonucString = sonucFormat.format(islemSonucu);

							SonucYazdir(txt_Sonuc, sonucString);
						}

					} else if (islemSonucu.compareTo(BigDecimal.ZERO) != 0) {		
						islemSonucu = islemSonucu.negate();

						SonucYazdir(txt_Sonuc, sonucFormat.format(islemSonucu));
					}

				} else {

					if (sayiGirildi) {
						BigDecimal tempSayi = new BigDecimal(girilenSayi);

						if (tempSayi.compareTo(BigDecimal.ZERO) != 0)
							girilenSayi = sonucFormat.format(tempSayi.negate());

					} else {

						if (islemSonucu.compareTo(BigDecimal.ZERO) != 0) 
							girilenSayi = sonucFormat.format(islemSonucu.negate());
						else
							girilenSayi = "0";

						sayiGirildi = true;

					}

					SonucYazdir(txt_Sonuc, girilenSayi);
				}

				if (!eksiKoyulabilir) {
					islemGecmisiTemp = "neg(" + islemGecmisiTemp + ")";
					SonucYazdir(txt_IslemGecmisi, islemGecmisi + " " + islemGecmisiTemp);
				}

			}
		});
		btn_IsaretCevir.setMargin(new Insets(0, 0, 0, 0));
		btn_IsaretCevir.setBounds(134, 32, 42, 26);
		pnl_Tuslar.add(btn_IsaretCevir);

		JButton btn_KareKok = new JButton("\u221A");
		btn_KareKok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ParantezHazirla();

				if (esittirTiklandi) {

					islemSonucu = kareKok(islemSonucu);

					SonucYazdir(txt_Sonuc, sonucFormat.format(islemSonucu));

				} else {

					if (sayiGirildi) {
						BigDecimal tempSayi = new BigDecimal(girilenSayi);
						girilenSayi = sonucFormat.format(kareKok(tempSayi));

					} else {
						sayiGirildi = true;

						girilenSayi = sonucFormat.format(kareKok(islemSonucu));
					}

					SonucYazdir(txt_Sonuc, girilenSayi);
				}

				islemGecmisiTemp = "sqrt(" + islemGecmisiTemp + ")";

				SonucYazdir(txt_IslemGecmisi, islemGecmisi + " " + islemGecmisiTemp);

				eksiKoyulabilir = false;

			}
		});
		btn_KareKok.setBounds(178, 32, 42, 26);
		pnl_Tuslar.add(btn_KareKok);
		btn_KareKok.setMargin(new Insets(0, 0, 0, 0));

		JButton btn_VirgulEkle = new JButton(",");
		btn_VirgulEkle.setName(",");
		btn_VirgulEkle.addActionListener(new numberAction());
		btn_VirgulEkle.setMargin(new Insets(0, 0, 0, 0));
		btn_VirgulEkle.setBounds(90, 148, 42, 26);
		pnl_Tuslar.add(btn_VirgulEkle);

		JButton btn_CE = new JButton("CE");
		btn_CE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (esittirTiklandi)
					islemSonucu = BigDecimal.ZERO;
				else
					girilenSayi = "0";

				sayiGirildi = true;
				eksiKoyulabilir = true;
				isaretDegisti = false;
				virgulKoyuldu = false;	
				sonucString = "";

				SonucYazdir(txt_Sonuc, "0");

				if (parantezGirildi) {
					parantezGirildi = false;
					islemGecmisiTemp = "";
					SonucYazdir(txt_IslemGecmisi, islemGecmisi);
				}
			}
		});
		btn_CE.setMargin(new Insets(0, 0, 0, 0));
		btn_CE.setBounds(46, 32, 42, 26);
		pnl_Tuslar.add(btn_CE);

		JButton btn_Temizle = new JButton("C");
		btn_Temizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				esittirTiklandi = false;
				sayiGirildi = false;
				parantezGirildi = false;
				isaretDegisti = false;
				virgulKoyuldu = false;
				eksiKoyulabilir = false;			
				islemSonucu = BigDecimal.ZERO;	
				girilenSayi = "0";
				siradakiIslem = "";
				islemGecmisi = "";
				islemGecmisiTemp = "";
				sonucString = "";

				txt_IslemGecmisi.setText("");
				SonucYazdir(txt_Sonuc, "0");

			}
		});
		btn_Temizle.setMargin(new Insets(0, 0, 0, 0));
		btn_Temizle.setBounds(90, 32, 42, 26);
		pnl_Tuslar.add(btn_Temizle);

		JButton btn_Sil = new JButton("\u2B05");
		btn_Sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (sayiGirildi && !parantezGirildi && !hafizayaKaydedildi) {

					if (esittirTiklandi) {
						sonucString = RakamSil(sonucString);
						islemSonucu = new BigDecimal(sonucString);

					} else
						girilenSayi = RakamSil(girilenSayi);
				}

			} 
		});
		btn_Sil.setMargin(new Insets(0, 0, 0, 0));
		btn_Sil.setBounds(2, 32, 42, 26);
		pnl_Tuslar.add(btn_Sil);

		JButton btn_Esittir = new JButton("=");
		btn_Esittir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (esittirTiklandi) {

					if (!girilenSayi.equals("0"))
						islemSonucu = IslemYap(islemSonucu, new BigDecimal(girilenSayi));

					if (parantezGirildi) {
						islemGecmisiTemp = "";
						SonucYazdir(txt_IslemGecmisi, islemGecmisi);
					}

				} else {

					if (sayiGirildi) {
						islemSonucu = IslemYap(islemSonucu, new BigDecimal(girilenSayi));

					} else {
						girilenSayi = sonucFormat.format(islemSonucu);
						islemSonucu = IslemYap(islemSonucu, islemSonucu);	
					}

					esittirTiklandi = true;

					txt_IslemGecmisi.setText("");
				}	

				sayiGirildi = false;
				isaretDegisti = false;
				virgulKoyuldu = false;
				parantezGirildi = false;
				eksiKoyulabilir = false;
				sonucString = "";
				islemGecmisi = "";
				islemGecmisiTemp = "";

				SonucYazdir(txt_Sonuc, sonucFormat.format(islemSonucu));

			}
		});
		btn_Esittir.setMargin(new Insets(0, 0, 0, 0));
		btn_Esittir.setBounds(178, 119, 42, 55);
		pnl_Tuslar.add(btn_Esittir);

		JPanel pnl_YaziAlanlari = new JPanel();
		pnl_YaziAlanlari.setBounds(11, 11, 222, 64);
		frmHesapMakinesi.getContentPane().add(pnl_YaziAlanlari);
		pnl_YaziAlanlari.setLayout(null);

		txt_Sonuc = new JTextField();
		txt_Sonuc.setName("");
		txt_Sonuc.setBorder(null);
		txt_Sonuc.setBounds(24, 24, 198, 40);
		pnl_YaziAlanlari.add(txt_Sonuc);
		txt_Sonuc.setBackground(Color.GRAY);
		txt_Sonuc.setText("0");
		txt_Sonuc.setEditable(false);
		txt_Sonuc.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txt_Sonuc.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_Sonuc.setColumns(10);
		
		txt_IslemGecmisi = new JTextField();
		txt_IslemGecmisi.setBorder(null);
		txt_IslemGecmisi.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_IslemGecmisi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_IslemGecmisi.setEditable(false);
		txt_IslemGecmisi.setColumns(10);
		txt_IslemGecmisi.setBackground(Color.GRAY);
		txt_IslemGecmisi.setBounds(0, 0, 222, 24);
		pnl_YaziAlanlari.add(txt_IslemGecmisi);
		
		txt_Memory = new JTextField();
		txt_Memory.setAutoscrolls(false);
		txt_Memory.setName("");
		txt_Memory.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Memory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_Memory.setEditable(false);
		txt_Memory.setColumns(10);
		txt_Memory.setBorder(null);
		txt_Memory.setBackground(Color.GRAY);
		txt_Memory.setBounds(0, 24, 24, 40);
		pnl_YaziAlanlari.add(txt_Memory);

		frmHesapMakinesi.setResizable(false);
		frmHesapMakinesi.setBounds(100, 100, 250, 296);
		frmHesapMakinesi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private class numberAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String basilanTus = RakamCevir( ( (JButton) e.getSource() ).getName() );

			isaretDegisti = false;
			eksiKoyulabilir = true;

			if (!sayiGirildi || parantezGirildi || hafizayaKaydedildi) {

				if (parantezGirildi) {
					islemGecmisiTemp = "";
					SonucYazdir(txt_IslemGecmisi, islemGecmisi);
				}

				sayiGirildi = true;
				parantezGirildi = false;
				hafizayaKaydedildi = false;

				if (basilanTus.startsWith("."))
					basilanTus = "0.";

				if (esittirTiklandi) {
					sonucString = basilanTus;
					islemSonucu = new BigDecimal(basilanTus);

				} else {	
					girilenSayi = basilanTus;
				}

				SonucYazdir(txt_Sonuc, basilanTus);

			} else {

				if (esittirTiklandi) {

					if (sonucString.equals("0") && !basilanTus.equals("."))
						sonucString = "";

					sonucString += basilanTus;
					islemSonucu = new BigDecimal(sonucString);

					SonucYazdir(txt_Sonuc, sonucString);

				} else {

					if (girilenSayi.equals("0") && !basilanTus.equals("."))
						girilenSayi = "";

					girilenSayi += basilanTus;

					SonucYazdir(txt_Sonuc, girilenSayi);

				}	

			}

		}	
	}

	private class operatorAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String islemTuru = ((JButton) e.getSource()).getName();

			if (esittirTiklandi) {

				if (!isaretDegisti) {
					isaretDegisti = true;				
					sayiGirildi = false;
					esittirTiklandi = false;
					virgulKoyuldu = false;

					girilenSayi = sonucFormat.format(islemSonucu);

					if (parantezGirildi) 
						islemGecmisi = islemGecmisiTemp;
					else
						islemGecmisi = girilenSayi;	

					islemGecmisiTemp = IsaretCevir(islemTuru);

					parantezGirildi = false;

					SonucYazdir(txt_Sonuc, girilenSayi);
				}

			} else {

				if (!isaretDegisti) {

					isaretDegisti = true;
					virgulKoyuldu = false;

					BigDecimal girilenTemp = new BigDecimal(girilenSayi);

					islemSonucu = IslemYap(islemSonucu, girilenTemp);

					if (parantezGirildi) 
						islemGecmisi += " " + islemGecmisiTemp;
					else
						islemGecmisi += islemGecmisiTemp + " " + sonucFormat.format(girilenTemp);

					parantezGirildi = false;

					islemGecmisiTemp = IsaretCevir(islemTuru);

					SonucYazdir(txt_Sonuc, sonucFormat.format(islemSonucu));

				} else {
					islemGecmisiTemp = IsaretCevir(islemTuru);
				}

				sayiGirildi = false;

			}	

			SonucYazdir(txt_IslemGecmisi, islemGecmisi + islemGecmisiTemp);

			siradakiIslem = islemTuru;
			eksiKoyulabilir = false;
		}
	}

	private class MemoryAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String butonAdi = ( (JButton) e.getSource() ).getName();
			
			if (esittirTiklandi) {
				
				if (islemSonucu.compareTo(BigDecimal.ZERO) != 0) {
					HafizayaIsle(butonAdi, islemSonucu);
					
					virgulKoyuldu = false;			
					eksiKoyulabilir = false;
					hafizayaKaydedildi = true;
					
					SonucYazdir(txt_Sonuc, sonucFormat.format(islemSonucu));				
				}
				
			} else {
				
				if (sayiGirildi) {
					BigDecimal tempSayi = new BigDecimal(girilenSayi);
					
					if (tempSayi.compareTo(BigDecimal.ZERO) != 0) {
						HafizayaIsle(butonAdi, tempSayi);
												
						virgulKoyuldu = false;			
						eksiKoyulabilir = false;
						hafizayaKaydedildi = true;
						
						SonucYazdir(txt_Sonuc, sonucFormat.format(tempSayi));
					}
				
				} else {		
					
					if (islemSonucu.compareTo(BigDecimal.ZERO) != 0)
						HafizayaIsle(butonAdi, islemSonucu);
					
				}				
			}
			
		}	
	}
	
	private void HafizayaIsle (String _tiklananButon, BigDecimal _islenecekSayi) {
		
		switch(_tiklananButon) {
		case "hafEkle":
				hafizaSayi = hafizaSayi.add(_islenecekSayi);
			break;
			
		case "hafCikar":
				hafizaSayi = hafizaSayi.subtract(_islenecekSayi);
			break;
			
		case "hafEsitle":
				hafizaSayi = _islenecekSayi;
			break;
		}
		
		if (hafizaSayi.compareTo(BigDecimal.ZERO) == 0) {
			txt_Memory.setText("");	
		
		} else {
			txt_Memory.setText("M");
		}
		
	}
	
	private BigDecimal IslemYap(BigDecimal _kaynakSayi, BigDecimal _hedefSayi) {

		try {

			switch(siradakiIslem) {
			case "Topla":
				_kaynakSayi = _kaynakSayi.add(_hedefSayi);
				break;

			case "Cikar":
				_kaynakSayi = _kaynakSayi.subtract(_hedefSayi);
				break;

			case "Carp":
				_kaynakSayi = _kaynakSayi.multiply(_hedefSayi);
				break;

			case "Bol":
				_kaynakSayi = _kaynakSayi.divide(_hedefSayi, 16, RoundingMode.HALF_UP);
				break;

			case "ModAl":
				_kaynakSayi = _kaynakSayi.remainder(_hedefSayi);
				break;

			default:
				_kaynakSayi = _hedefSayi;
			}	

		} catch(ArithmeticException e) {
			_kaynakSayi = BigDecimal.ZERO;
		}

		return _kaynakSayi;

	}

	private String RakamCevir(String _butonAdi) {

		byte rakam;

		try {  		
			rakam = Byte.parseByte(_butonAdi);  

		} catch(NumberFormatException e) {  

			if (!virgulKoyuldu) {
				virgulKoyuldu = true;
				return ".";

			} else {
				return "";
			}
		}  

		return Byte.toString(rakam); 

	}

	private String IsaretCevir(String _butonAdi) {

		String donusDegeri = "";

		switch(_butonAdi) {
		case "Topla":
			donusDegeri = " +";
			break;

		case "Cikar":
			donusDegeri = " -";
			break;

		case "Carp":
			donusDegeri = " *";
			break;

		case "Bol":
			donusDegeri = " /";
			break;

		case "ModAl":
			donusDegeri = " mod";
			break;
		}

		return donusDegeri;

	}

	private void ParantezHazirla() {

		if (!parantezGirildi) {
			parantezGirildi = true;
			isaretDegisti = false;

			islemGecmisi += islemGecmisiTemp;

			if (esittirTiklandi) {
				islemGecmisiTemp = sonucFormat.format(islemSonucu);

			} else {

				if (sayiGirildi) {			
					islemGecmisiTemp = sonucFormat.format( new BigDecimal(girilenSayi) );

				} else {
					islemGecmisiTemp = sonucFormat.format(islemSonucu);
				}

			}
		}

	}

	private BigDecimal kareKok(BigDecimal _x) {

		if (_x.compareTo(BigDecimal.ZERO) > 0) {			
			BigDecimal epsilon = new BigDecimal(1e-15); 
			BigDecimal t = _x;   

			/*
			x >= 0 için:
		  	| _x - c/t | > epsilon * t
		   	t * ( t - _x / t ) > epsilon
		  	tt - _x = > epsilon
			 */

			do {

				t = _x.divide(t, 16, BigDecimal.ROUND_HALF_UP).add(t).divide(new BigDecimal(2.0), 16, BigDecimal.ROUND_HALF_UP);

			} while ( t.subtract( _x.divide(t, 16, BigDecimal.ROUND_HALF_UP) ).abs().compareTo( epsilon.multiply(t) ) > 0 );

			return t;

		} else
			return BigDecimal.ZERO;

	}

	private String RakamSil(String _kaynakSayi) {

		int imlec = _kaynakSayi.length() - 1;

		if (_kaynakSayi.charAt(imlec) == '.')
			virgulKoyuldu = false;

		if (imlec < 1 || _kaynakSayi.charAt(imlec - 1) == '-')
			_kaynakSayi = "0";	
		else
			_kaynakSayi = _kaynakSayi.substring(0, imlec);

		SonucYazdir(txt_Sonuc, _kaynakSayi);

		return _kaynakSayi;

	}
	
	private void SonucYazdir(JTextField _alan, String _sonuc) {	

		_alan.setText( _sonuc.replace(".", ",") );	

		if (_alan.equals(txt_Sonuc)) {		
			txt_Sonuc.setFont(sonucFont);

			int uzunluk = txt_Sonuc.getFontMetrics(txt_Sonuc.getFont()).stringWidth(_sonuc);

			while(uzunluk > txt_Sonuc.getWidth()) {
				Font eskiFont = txt_Sonuc.getFont();
				txt_Sonuc.setFont( new Font(eskiFont.getName(), eskiFont.getStyle(), eskiFont.getSize() - 1) );

				uzunluk = txt_Sonuc.getFontMetrics(txt_Sonuc.getFont()).stringWidth(_sonuc);
			}				
		}

	}
}
