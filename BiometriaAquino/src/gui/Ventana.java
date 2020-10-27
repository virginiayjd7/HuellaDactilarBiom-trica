package gui;


import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import huellas.GestorHuellas;
import huellas.HuellaDactilar;
import huellas.Minucia;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ventana {
	
	// Instancia del gestor de huellas
	private GestorHuellas gh;

	// Frame de la interfaz
	private JFrame frame;
	
	// Paneles donde se muestran las huellas
	private JPanel panelBordeIzquierda;
	private JPanel panelHuellaIzquierda;
	private JPanel panelBordeDerecha;
	private JPanel panelHuellaDerecha;
	
	// Graphics del JPanel utilizados para pintar en la interfaz
	private Graphics gIzquierda;
	private Graphics gDerecha;
	
	// Huellas mostradas en la interfaz
	HuellaDactilar huellaIzquierda;
	HuellaDactilar huellaDerecha;
	
	// Botones de la interfaz
	private JButton btnCargarHuella;
	private JButton btnDeshacer;
	private JButton btnReiniciar;
	private JButton btnGrises;
	private JButton btnEcualizar;
	private JButton btnUmbralizar;
	private JSlider sliderUmbral;
	private JButton btnFiltrar;
	private JButton btnAdelgazar;
	private JLabel lblLmite;
	private JSpinner spinnerLimite;
	private JButton btnMinucias;
	private JButton btnAngulos;
	private JButton btnExportarDatos;


	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
		
		gh = new GestorHuellas();
		huellaIzquierda = null;
		huellaDerecha = null;
		
		frame.setVisible( true );
		
		gIzquierda = panelHuellaIzquierda.getGraphics();
		panelHuellaIzquierda.paintComponents( gIzquierda );
		
		gDerecha = panelHuellaDerecha.getGraphics();
		panelHuellaDerecha.paintComponents( gDerecha );
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds( 100 , 100 , 920 , 570 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable( false );
		frame.getContentPane().setLayout(null);
		
		// PANEL DE LA HUELLA IZQUIERDA
		panelBordeIzquierda = new JPanel();
		panelBordeIzquierda.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Huella original", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBordeIzquierda.setBounds(6, 40, 372, 504);
		frame.getContentPane().add(panelBordeIzquierda);
		panelBordeIzquierda.setLayout(null);
		
		panelHuellaIzquierda = new JPanel();
		panelHuellaIzquierda.setBounds(6, 18, 360, 480);
		panelBordeIzquierda.add(panelHuellaIzquierda);
		
		// PANEL DE LA HUELLA DERECHA
		panelBordeDerecha = new JPanel();
		panelBordeDerecha.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Huella tratada", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBordeDerecha.setBounds(542, 40, 372, 504);
		frame.getContentPane().add(panelBordeDerecha);
		panelBordeDerecha.setLayout(null);
		
		panelHuellaDerecha = new JPanel();
		panelHuellaDerecha.setBounds(6, 18, 360, 480);
		panelBordeDerecha.add(panelHuellaDerecha);
	
		// Listener que ejecuta cuando se restaura la ventana y pinta los paneles de nuevo en la interfaz
		frame.addWindowListener( new WindowAdapter() {
			
			//TODO: Pintar huellas al restaurar la ventana
			
			public void windowDeiconified(WindowEvent e) {
	            System.out.println("Estoy restaurando");
	            
	            if( btnGrises.isEnabled() ) {
		            System.out.println("Boton grises enabled");
		            
		            // Ultimo paso ejecutado CARGAR IMAGEN
		            
		            panelHuellaIzquierda.repaint();
		            
	            } else if( btnEcualizar.isEnabled() ) {
		            System.out.println("Boton ecualizar enabled");
		            
		            // Ultimo paso ejecutado GRISES
	            	
	            } else if( btnUmbralizar.isEnabled() ) {
		            System.out.println("Boton umbralizar enabled");
		            
		            // Ultimo paso ejecutado ECUALIZAR
		            
	            	
	            } else if( btnFiltrar.isEnabled() ) {
		            System.out.println("Boton filtrar enabled");
		            
		            // Ultimo paso ejecutado UMBRALIZAR
	            	
	            } else if( btnAdelgazar.isEnabled() ) {
		            System.out.println("Boton adelgazar enabled");
		            
		            // Ultimo paso ejecutado FILTRAR
	            	
	            } else if( btnMinucias.isEnabled() ) {
		            System.out.println("Boton minucias enabled");
		            
		            // Ultimo paso ejecutado ADELGAZAR
	            	
	            } else if( btnAngulos.isEnabled() ) {
		            System.out.println("Boton Ã¡ngulos enabled");
		            
		            // Ultimo paso ejecutado MINUCIAS
	            	
	            }
	            
	        }
			
		});
		
		
		// ===== BOTÃ“N PARA CARGAR LA HUELLA =====
		btnCargarHuella = new JButton("Cargar Huella");
		btnCargarHuella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Creamos el objeto JFileChooser
		        JFileChooser fc = new JFileChooser();
		        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter( "JPG , PNG & GIF" , "jpg" , "png" , "gif" );
		        fc.setFileFilter( filtroImagen );
		        
		        // Abrimos la ventana, guardamos la opcion seleccionada por el usuario
		        int seleccion = fc.showOpenDialog( frame );
		        
		        // Si el usuario, pincha en aceptar
		        if( seleccion == JFileChooser.APPROVE_OPTION ){
		         
		            //Seleccionamos el fichero
		            File fichero = fc.getSelectedFile();
		         
		            try {
		            	
						BufferedImage huella = ImageIO.read( new File( fichero.getAbsolutePath() ) );
						
						gh.setHuellaOriginal( huella );
						
						pintarPanelIzquierda( huella );
						
						btnGrises.setEnabled( true );
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            
		        }
			}
		});
		
		btnCargarHuella.setBounds(7, 7, 117, 29);
		frame.getContentPane().add(btnCargarHuella);
		
		// BOTÃ“N DESHACER
		btnDeshacer = new JButton("Deshacer");
		btnDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deshacer();
				
			}
		});
		btnDeshacer.setBounds(124, 7, 117, 29);
		frame.getContentPane().add(btnDeshacer);
		btnDeshacer.setEnabled( false );
		
		// BOTÃ“N REINICIAR
		btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				resetear();
				
			}
		});
		btnReiniciar.setBounds(241, 7, 117, 29);
		frame.getContentPane().add(btnReiniciar);
		btnReiniciar.setEnabled( false );
		
		// BOTÃ“N PARA CONVERTIR LA HUELLA A ESCALA DE GRISES
		btnGrises = new JButton("Grises");
		btnGrises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Convertimos la huella original a Grises y la almacenamos en la huella derecha
				huellaDerecha = gh.convertirGrises( gh.getHuellaOriginal() );

				// Pinta en el panel de la derecha la huella de la derecha
				pintarPanelDerecha( GestorHuellas.HUELLA_GRIS );

				// Actualizamos el estado de los botones de la interfaz
				btnGrises.setEnabled( false );
				btnEcualizar.setEnabled( true );
				btnReiniciar.setEnabled( true );
				
			}
		});
		
		btnGrises.setBounds(390, 55, 135, 29);
		frame.getContentPane().add(btnGrises);
		btnGrises.setEnabled( false );
		
		// BOTÃ“N PARA ECUALIZAR
		btnEcualizar = new JButton("Ecualizar");
		btnEcualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Copiamos la huella del panel de la derecha a la izquierda
				huellaIzquierda = huellaDerecha;
				
				// Pintamos la huella de la izquierda
				pintarPanelIzquierda( GestorHuellas.HUELLA_GRIS );
				
				// Aplicamos el ecualizado a la huella derecha
				huellaDerecha = gh.ecualizado( huellaDerecha );

				// Pintamos la huella de la derecha
				pintarPanelDerecha( GestorHuellas.HUELLA_GRIS );
				
				// Actualizamos el estado de los botones de la interfaz
				btnEcualizar.setEnabled( false );
				btnUmbralizar.setEnabled( true );
				sliderUmbral.setEnabled( true );
				sliderUmbral.setValue( gh.getUmbralMedio() );
				
			}
		});
		btnEcualizar.setBounds(390, 83, 135, 29);
		frame.getContentPane().add(btnEcualizar);
		btnEcualizar.setEnabled( false );
		
		
		// BOTÃ“N PARA UMBRALIZAR
		btnUmbralizar = new JButton("Umbralizar");
		btnUmbralizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// La huella del panel izquierdo desaparece de la interfaz, se almacena en el historial de deshacer
				gh.almacenarEnHistorial( huellaIzquierda );

				// Copiamos la huella del panel de la derecha a la izquierda
				huellaIzquierda = huellaDerecha;				
				
				// Pintamos la huella de la izquierda
				pintarPanelIzquierda( GestorHuellas.HUELLA_GRIS );
				
				// Aplicamos el umbralizado a la huella derecha
				huellaDerecha = gh.umbralizar( huellaDerecha , sliderUmbral.getValue() );
				
				// Pintamos la huella de la derecha
				pintarPanelDerecha( GestorHuellas.HUELLA_BYN );
				
				// Actualizamos el estado de los botones de la interfaz
				btnUmbralizar.setEnabled( false );
				sliderUmbral.setEnabled( false );
				btnFiltrar.setEnabled( true );

				// Se activa la funciÃ³n deshacer
				btnDeshacer.setEnabled( true );

				
			}
		});
		btnUmbralizar.setBounds(390, 111, 135, 29);
		frame.getContentPane().add(btnUmbralizar);
		btnUmbralizar.setEnabled( false );
		
		// SLIDER PARA MARCAR EL UMBRAL
		sliderUmbral = new JSlider( 0 , 256 );
		sliderUmbral.setMinorTickSpacing(16);
		sliderUmbral.setPaintLabels( true );
		sliderUmbral.setPaintTicks( true );
		sliderUmbral.setMajorTickSpacing(64);
		sliderUmbral.setBounds(385, 153, 145, 39);
		frame.getContentPane().add(sliderUmbral);
		sliderUmbral.setEnabled( false );
		
		
		// BOTÃ“N PARA FILTRAR
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// La huella del panel izquierdo desaparece de la interfaz, se almacena en el historial de deshacer
				gh.almacenarEnHistorial( huellaIzquierda );

				// Copiamos la huella del panel de la derecha a la izquierda
				huellaIzquierda = huellaDerecha;				
				
				// Pintamos la huella de la izquierda
				pintarPanelIzquierda( GestorHuellas.HUELLA_BYN );
				
				// Aplicamos el filtrado a la huella derecha
				huellaDerecha = gh.filtrar( huellaDerecha );
				
				// Pintamos la huella de la derecha
				pintarPanelDerecha( GestorHuellas.HUELLA_BYN );
				
				// Actualizamos el estado de los botones de la interfaz
				btnFiltrar.setEnabled( false );
				btnAdelgazar.setEnabled( true );
				
			}
		});
		btnFiltrar.setBounds(390, 204, 135, 29);
		frame.getContentPane().add(btnFiltrar);
		btnFiltrar.setEnabled( false );
		
		// BOTÃ“N PARA ADELGAZAR
		btnAdelgazar = new JButton("Adelgazar");
		btnAdelgazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// La huella del panel izquierdo desaparece de la interfaz, se almacena en el historial de deshacer
				gh.almacenarEnHistorial( huellaIzquierda );

				// Copiamos la huella del panel de la derecha a la izquierda
				huellaIzquierda = huellaDerecha;				
				
				// Pintamos la huella de la izquierda
				pintarPanelIzquierda( GestorHuellas.HUELLA_BYN );
				
				// Aplicamos el adelgazado a la huella derecha
				huellaDerecha = gh.adelgazar( huellaDerecha );
				
				// Pintamos la huella de la derecha
				pintarPanelDerecha( GestorHuellas.HUELLA_BYN );
				
				// Actualizamos el estado de los botones de la interfaz
				btnAdelgazar.setEnabled( false );
				btnMinucias.setEnabled( true );
				lblLmite.setEnabled( true );
				spinnerLimite.setEnabled( true );
				
			}
		});
		btnAdelgazar.setBounds(390, 232, 135, 29);
		frame.getContentPane().add(btnAdelgazar);
		btnAdelgazar.setEnabled( false );
		
		// SPINNER PARA MARCAR LOS LÃ�MITES AL DETECTAR MINUCIAS
		lblLmite = new JLabel( "Limite" );
		lblLmite.setBounds(410, 281, 61, 16);
		frame.getContentPane().add(lblLmite);
		lblLmite.setEnabled( false );
		
		spinnerLimite = new JSpinner();
		spinnerLimite.setBounds(450, 276, 60, 26);
		spinnerLimite.setValue( 10 );
		frame.getContentPane().add( spinnerLimite );
		spinnerLimite.setEnabled( false );
				
		// BOTÃ“N MINUCIAS
		btnMinucias = new JButton("Minucias");
		btnMinucias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// La huella del panel izquierdo desaparece de la interfaz, se almacena en el historial de deshacer
				gh.almacenarEnHistorial( huellaIzquierda );

				// Copiamos la huella del panel de la derecha a la izquierda
				huellaIzquierda = huellaDerecha;				
				
				// Pintamos la huella de la izquierda
				pintarPanelIzquierda( GestorHuellas.HUELLA_BYN );
				
				// Reiniciamos la lista de minucias y Ã¡ngulos para que almacene las detectadas
				gh.reiniciarMinucias();
				gh.reiniciarAngulos();
				
				// Detectamos las minucias en la huella derecha
				gh.detectarMinucias( huellaDerecha , (int) spinnerLimite.getValue() );
				
				// Pintamos la huella de la derecha
				BufferedImage huellaMinucias = gh.convertirRGB( huellaDerecha , GestorHuellas.HUELLA_BYN );
				pintarMinucias( huellaMinucias );
				pintarPanelDerecho( huellaMinucias );
				
				// Actualizamos el estado de los botones de la interfaz
				btnMinucias.setEnabled( false );
				lblLmite.setEnabled( false );
				spinnerLimite.setEnabled( false );
				btnAngulos.setEnabled( true );
				
			}
		});
		btnMinucias.setBounds(390, 311, 135, 29);
		frame.getContentPane().add(btnMinucias);
		btnMinucias.setEnabled( false );
		
		// BOTÃ“N Ã�NGULOS
		btnAngulos = new JButton("Angulos");
		btnAngulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Calculamos los Ã¡ngulos
				gh.calcularAngulos( huellaDerecha );

				// Creamos el cuadro de diÃ¡logo y lo mostramos
				Dialogo dialog = new Dialogo();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			
				
				// Imprimimos el contenido a mostrar en el diÃ¡logo
				dialog.imprimirLinea( "INFORMACION SOBRE MINUCIAS:" );
				dialog.imprimirLinea( "---------------------------------" );
				dialog.imprimirLinea( "Numero de minucias detectadas: " + gh.getListaMinucias().size() );
				
				Minucia aux;
				Double angulo;
				for( int i = 0 ; i < gh.getListaMinucias().size() ; i++ ) {
					aux = gh.getListaMinucias().get( i );
					angulo = gh.getListaAngulos().get( i );
					dialog.imprimirLinea( "  " + i + " en (" + aux.getX() + "," + aux.getY() + ") con Angulo " + angulo );
				}
				
				// Actualizamos el estado de los botones de la interfaz
				btnExportarDatos.setEnabled( true );
				
			}
		});
		btnAngulos.setBounds(390, 340, 135, 29);
		frame.getContentPane().add(btnAngulos);
		btnAngulos.setEnabled( false );
		
		// BOTÃ“N EXPORTAR DATOS A FICHERO
		btnExportarDatos = new JButton("Exportar datos");
		btnExportarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( gh.escribirFichero( "minucias.csv" ) ) {
					JOptionPane.showMessageDialog(null, "Operacion realizada correctamente" , "Biometria" , JOptionPane.INFORMATION_MESSAGE );
					btnExportarDatos.setEnabled( false );
				} else {
					JOptionPane.showMessageDialog( null , "Se produjo un error en la operacion!" , "ERROR" , JOptionPane.ERROR_MESSAGE );
				}
				
			}
		});
		btnExportarDatos.setBounds(390, 369, 135, 29);
		frame.getContentPane().add(btnExportarDatos);
		btnExportarDatos.setEnabled( false );
		
	}
	
	/**
	 * Pinta en el marco izquierdo de la interfaz la BufferedImage pasada por parÃ¡metros
	 * @param huellaAMostrar la imagen de la huella que se desea mostrar en la interfaz
	 */
	private void pintarPanelIzquierda( BufferedImage huellaAMostrar ) {
		Graphics g = panelHuellaIzquierda.getGraphics();
		panelHuellaIzquierda.paintComponents( g );
		g.drawImage( huellaAMostrar , 0 , 0 , null );
	}

	/**
	 * Pinta en el marco derecho de la interfaz la BufferedImage pasada por parÃ¡metros
	 * @param huellaAMostrar la imagen de la huella que se desea mostrar en la interfaz
	 */
	private void pintarPanelDerecho( BufferedImage huellaAMostrar ) {
		Graphics g = panelHuellaDerecha.getGraphics();
		panelHuellaDerecha.paintComponents( g );
		g.drawImage( huellaAMostrar , 0 , 0 , null );
	}
	

	/**
	 * Pinta la huella izquierda en el panel izquierdo
	 * @param modo modo de imagen a pintar
	 */
	private void pintarPanelIzquierda( int modo ) {
		BufferedImage imagenHuellaAMostrar = gh.convertirRGB( huellaIzquierda , modo );
		gIzquierda.drawImage( imagenHuellaAMostrar , 0 , 0 , null );
	}
	
	/**
	 * Pinta la huella derecha en el panel derecho
	 * @param modo modo de imagen a pintar
	 */
	private void pintarPanelDerecha( int modo ) {
		BufferedImage imagenHuellaAMostrar = gh.convertirRGB( huellaDerecha , modo );
		gDerecha.drawImage( imagenHuellaAMostrar , 0 , 0 , null );
	}
	
	/**
	 * Resetea la aplicaciÃ³n a su estado inicial, con la huella original cargada en la izquierda
	 */
	private void resetear() {
		
		// Pinta huella original en el panel izquierdo
		pintarPanelIzquierda( gh.getHuellaOriginal() );
		
		// Borramos lo pintado en el panel de la derecha
		Graphics g = panelHuellaDerecha.getGraphics();
		panelHuellaDerecha.paintComponents(g);
		g.clearRect( 0 , 0 , panelHuellaDerecha.getWidth() , panelHuellaDerecha.getHeight() );
		
		// Eliminamos el hisotiral de deshacer
		gh.reiniciarHistorial();
		
		// Desactivamos botones de reiniciar e historial
		btnReiniciar.setEnabled( false );
		btnDeshacer.setEnabled( false );
		
		// Colocamos el estado de inicio de los botones de la interfaz
		btnGrises.setEnabled( true );
		btnEcualizar.setEnabled( false );
		btnUmbralizar.setEnabled( false );
		sliderUmbral.setEnabled( false );
		btnFiltrar.setEnabled( false );
		btnAdelgazar.setEnabled( false );
		btnMinucias.setEnabled( false );
		lblLmite.setEnabled( false );
		spinnerLimite.setEnabled( false );
		btnAngulos.setEnabled( false );
		btnExportarDatos.setEnabled( false );
		
	}
	
	/**
	 * MÃ©todo que implementa la funciÃ³n deshacer
	 */
	private void deshacer() {
		
		if( btnFiltrar.isEnabled() ) {	// PREVIO    --> Panel izquierdo: Ecualizada	Panel derecho: Umbralizada	Historial: Grises
										// RESULTADO --> Panel izquierdo: Grises	Panel derecho: Ecualizada	Historial: -
			
			// En el panel derecho mostramos el contenido del izquierdo
			huellaDerecha = huellaIzquierda;
			pintarPanelDerecha( GestorHuellas.HUELLA_GRIS );
			
			// Recuperamos para el panel izquierdo la huella en grises
			huellaIzquierda = gh.recuperarDeHistorial();
			pintarPanelIzquierda( GestorHuellas.HUELLA_GRIS );
			
			// Cambiamos el estado de los botones
			btnUmbralizar.setEnabled( true );
			sliderUmbral.setEnabled( true );
			btnFiltrar.setEnabled( false );
			
			// Ya no podemos deshacer mÃ¡s pasos, desactivamos el botÃ³n
			btnDeshacer.setEnabled( false );
			
		} else if( btnAdelgazar.isEnabled() ) {	// PREVIO    --> Panel izquierdo: Umbralizada	Panel derecho: Filtrada	Historial: Ecualizada/Grises
												// RESULTADO --> Panel izquierdo: Ecualizada	Panel derecho: Umbralizada	Historial: Grises
			
			// En el panel derecho mostramos el contenido del izquierdo
			huellaDerecha = huellaIzquierda;
			pintarPanelDerecha( GestorHuellas.HUELLA_BYN );
			
			// Recuperamos para el panel izquierdo la huella ecualizada
			huellaIzquierda = gh.recuperarDeHistorial();
			pintarPanelIzquierda( GestorHuellas.HUELLA_GRIS );
			
			// Cambiamos el estado de los botones
			btnFiltrar.setEnabled( true );
			btnAdelgazar.setEnabled( false );
			
		} else if( btnMinucias.isEnabled() ) {	// PREVIO    --> Panel izquierdo: Filtrada	Panel derecho: Adelgazada	Historial: Umbralizada/Ecualizada/Grises
												// RESULTADO --> Panel izquierdo: Umbralizada	Panel derecho: Filtrada	Historial: Ecualizada/Grises
			
			// En el panel derecho mostramos el contenido del izquierdo
			huellaDerecha = huellaIzquierda;
			pintarPanelDerecha( GestorHuellas.HUELLA_BYN );
			
			// Recuperamos para el panel izquierdo la huella ecualizada
			huellaIzquierda = gh.recuperarDeHistorial();
			pintarPanelIzquierda( GestorHuellas.HUELLA_BYN );
			
			// Cambiamos el estado de los botones
			btnAdelgazar.setEnabled( true );
			btnMinucias.setEnabled( false );
			lblLmite.setEnabled( false );
			spinnerLimite.setEnabled( false );
			
		} else if( btnAngulos.isEnabled() ) {	// PREVIO    --> Panel izquierdo: Adelgazada	Panel derecho: Minucias	Historial: Filtrada/Umbralizada/Ecualizada/Grises
												// RESULTADO --> Panel izquierdo: Filtrada	Panel derecho: Adelgazada	Historial: Umbralizada/Ecualizada/Grises
			
			// En el panel derecho mostramos el contenido del izquierdo
			huellaDerecha = huellaIzquierda;
			pintarPanelDerecha( GestorHuellas.HUELLA_BYN );
			
			// Recuperamos para el panel izquierdo la huella ecualizada
			huellaIzquierda = gh.recuperarDeHistorial();
			pintarPanelIzquierda( GestorHuellas.HUELLA_BYN );
			
			// Cambiamos el estado de los botones
			btnMinucias.setEnabled( true );
			lblLmite.setEnabled( true );
			spinnerLimite.setEnabled( true );
			btnAngulos.setEnabled( false );
			btnExportarDatos.setEnabled( false );
			
		}
		
	}
	
	/**
	 * MÃ©todo que pinta las minucias en la imagen a mostrar pasada por parÃ¡metros
	 * @param img la imagen que representa la huella sobre la que se han calculado las minucias
	 */
	private void pintarMinucias( BufferedImage img ) {
		
		int azul = (255<<24 | 0 << 16 | 0 << 8 | 255);
		int rojo = (255<<24 | 255 << 16 | 0 << 8 | 0);
		
		int pixelRGB;
		Minucia aux;
				
		for( int i = 0 ; i < gh.getListaMinucias().size() ; i++ ){
			
			aux = gh.getListaMinucias().get(i);
			
			if( aux.getTipo() == 1 ) {
				pixelRGB = azul;			// Si la minucia es tipo corte, la pinta en azul				
			} else {
				pixelRGB = rojo;			// Si la minucia es tipo bifurcaciÃ³n, la pinta en rojo
			}
			
			img.setRGB( aux.getX() , aux.getY() , pixelRGB );		// Pixel que representa la minucia
			
			img.setRGB( aux.getX() + 1 , aux.getY() + 1, pixelRGB );		// Pixeles que rodean la minucia
			img.setRGB( aux.getX() - 1 , aux.getY() - 1, pixelRGB );
			img.setRGB( aux.getX() - 1 , aux.getY() + 1, pixelRGB );
			img.setRGB( aux.getX() + 1 , aux.getY() - 1, pixelRGB );
			img.setRGB( aux.getX()     , aux.getY() + 1, pixelRGB );
			img.setRGB( aux.getX()     , aux.getY() - 1, pixelRGB );
			img.setRGB( aux.getX() + 1 , aux.getY()    , pixelRGB );
			img.setRGB( aux.getX() - 1 , aux.getY()    , pixelRGB );
		}
		
	}
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
