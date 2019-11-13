import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Ecrit{
	
	static String Chrono(long debut,long fin)
	{
		String temps ="";
		int Seconde,Minutes;
		double seconde = (fin-debut)/1000000000;
		if(seconde > 60)
		{
			double minutes = Math.round(seconde/60);
			Minutes = (int) minutes;
			seconde = seconde % 60;
			Seconde = (int)seconde;
			 temps = Minutes+"min "+Seconde+"sec";
		}
		else{
			 Seconde = (int)seconde;
			temps = Seconde +" secondes" ;
		}
		return temps;
	}
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException
		{
		String sl = FileSystems.getDefault().getSeparator();
		String version = System.clearProperty("os.name");
		version= version.replaceAll("[0-9]", "");
		version= version.replaceAll(" ", "");
		String fich = null,fic;
		
		int i=1;
		boolean vf=false;
		String rep ="Oui",rep2 ="Oui";
		System.out.println("-------------------Ce programme sert a la copie ou au deplacement de fichier grace � leurs adresse------------------------");
		Thread.sleep(200);
		
		Scanner sc = new Scanner(System.in);
		
		Scanner sc2 =new Scanner(System.in);
	
		Scanner cc = new Scanner(System.in);
		
		int choix=0 ;
		
		while(rep.equals("Oui") || rep.equals("oui"))
		{
			
			if(rep2.equals("Non")|| rep2.equals("non"))
			{
				System.out.println("Souhaitez vous continuer le programme ?\n-Oui\t-Non");
				rep = sc.next();
				
					while(!rep.equals("Non") && !rep.equals("non") && !rep.equals("Oui") && !rep.equals("oui"))
					{
					System.out.println("Veuillez bien orthographier Oui ou Non !");
					rep = sc.next();
					}
					
				}
			
			if(rep.equals("Oui") || rep.equals("oui")){
			System.out.println("Souhaitez-vous:\n0.Copier le fichier cible\n1.Deplacer le fichier cible\nR�pondre par [0/1] ");
			try{
				choix = cc.nextInt();
			}catch(InputMismatchException e)
			{
				 i =0;
				System.out.println("Tu n'as pas rentr� de nombre !");
				System.out.println("Progam failed");
				System.out.println("Sortie du programme code d'erreur :"+e.getMessage());
				rep = "non";
			}
		switch (choix) {
		
		case 0 :
			FileChannel fc = null;
			BufferedOutputStream bo= null ;
			FileInputStream fi;
			
			rep2 ="Oui";
			if(i == 0)
			{
				rep2="non";
			}
			 
			
			
			while(rep2.equals("Oui") || rep2.equals("oui")){
				long r = 1, pour=0, nbr=1;
				sc = new Scanner(System.in);
				cc = new Scanner(System.in);
				sc2 =new Scanner(System.in);
			System.out.println("\n------------------------------------------------------------------");
			System.out.println("------------------------------------------------------------------");
			System.out.println("Saisissez le chemin complet du fichier � copier :");
			
			
				fich = sc.nextLine();
				fich = fich.replaceAll("\"","");
				
			
			System.out.println("\nSaisissez le nom de la copie :");
			String cop = sc2.nextLine();
		
			try
			{
				
				
				File f = new File("Info.txt");
				String d = "Info.txt";
				String way = f.getAbsolutePath(); 
				way = way.replaceFirst(d, sl+"Copie");
				File ne = new File(way);
				ne.mkdir();
				
				File test = new File(way+sl+cop);
				boolean tf = test.exists() ;
				
				if(tf == true)
				{
					System.out.println("\nLe fichier que vous voulez cr�er existe d�j�,voulez vous l'�craser ou �crire � la suite ? :\n-Effacer\n-Copier");
					String ec = sc.next();
						if(ec.equals("Copier") || ec.equals("copier"))
						{
							vf = true;
						}
						else if(ec.equals("Effacer") || ec.equals("effacer"))
						{
							
						}
						else
						{
								while(!ec.equals("Copier") && !ec.equals("copier") && !ec.equals("Effacer") && !ec.equals("effacer"))
								{
									System.out.println("-------------------------------------------------");
									System.out.println("Veuillez bien orthographier ! : \n-Effacer\n-Copier");
									 ec = sc.next();
								}
								if(ec.equals("Copier") || ec.equals("copier"))
								{
									vf = true;
								}
								
									
						}
				}
				
				
				fi = new FileInputStream(new File(fich));
				bo= new BufferedOutputStream(new FileOutputStream(new File(way+sl+cop),vf));
				
				long sT = System.nanoTime();
				System.out.println("Lecture du fichier en cours ...");
				fc = fi.getChannel();
				int taille = (int)fc.size();
				ByteBuffer by = ByteBuffer.allocate(taille);
				fc.read(by);
				by.flip();
				byte TabByte[]=by.array();
				long tab =  TabByte.length;
				System.out.println("\nEcriture en cours ...");
				for(byte lu : TabByte)
				{
					pour = (nbr*100)/tab;
					if(pour == r)
					{
						
						r++;
						System.out.println("Copie termin�e � :"+pour+"%");
						
						
					}
					bo.write(lu);
					nbr++;
					
				}
				
				System.out.println("\n------------------------------------------------------------------------");
				long eT = System.nanoTime();
				System.out.println("Temps de la copie du fichier : "+Chrono(sT,eT)+ ".");
				System.out.println("Voulez-vous de nouveau copier un fichier ?\n-Oui\t-Non");
					rep2 = sc.next();
					if(rep2.equals("Non") || rep2.equals("non"))
					{
						System.out.println("------------------------------------------------------------------------");
						System.out.println("Copie termin�");
						
					}
					else if(rep2.equals("Oui") || rep2.equals("oui"))
					{
					}
					else
					{
						while(!rep2.equals("Non") && !rep2.equals("non") && !rep2.equals("Oui") && !rep2.equals("oui"))
						{
						System.out.println("Veuillez bien orthographier Oui ou Non !");
						rep2 = sc.next();
						}
						if(rep2.equals("Non") || rep2.equals("non"))
						{
							System.out.println("------------------------------------------------------------------------");
							System.out.println("Copie termin�");
						}
					}
				
			
				
			}
			catch (FileNotFoundException e)
			{
				System.out.println(e.getMessage()+"\n");
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage()+"\n");
			}
			finally
			{
				try{
					if(fc != null)
					{
						fc.close();
					}
				} catch(IOException e)
				{
					System.out.println(e.getMessage()+"\n");
				}
				try{
					if(bo!= null)
						bo.close();
				}catch(IOException e){
					System.out.println(e.getMessage()+"\n");
					
				}
				
				}
			}
			break;
			case 1 :
				rep2 ="Oui";
				while(rep2.equals("Oui") || rep2.equals("oui")){
					sc = new Scanner(System.in);
					cc = new Scanner(System.in);
					sc2 =new Scanner(System.in);
					System.out.println("\n------------------------------------------------------------------");
					System.out.println("------------------------------------------------------------------");
				System.out.println("Saisissez le chemin complet du fichier � d�placer\n!*Attention si ce fichier existe d�j� il sera �craser automatiqument*! :");
				
				fic = sc.nextLine();
				fic = fic.replaceAll("\"","");
					
				System.out.println("\nSaisissez le nom du fichier d�plac� :");
				String co = sc.next();
				long sT = System.nanoTime();
				System.out.println("\nDeplacement en cours...\n");
				File f = new File("Info.txt");
				String d = "Info.txt";
				String way = f.getAbsolutePath(); 
				way = way.replaceFirst(d, sl+"Copie");
				File ne = new File(way);
				ne.mkdir();
				
				Path source = Paths.get(fic);
				Path cible = Paths.get(way+sl+co);
				
				
				
				try{
					
					Files.move(source, cible,StandardCopyOption.REPLACE_EXISTING);
					long eT = System.nanoTime();
					System.out.println("Temps du deplacement du fichier : "+Chrono(sT,eT)+ ".");
					System.out.println("Deplacement termin�");
					System.out.println("Voulez-vous de nouveau d�placer un fichier ?\n-Oui\t-Non");
					rep2= sc.next();
					if(rep2.equals("Non") || rep2.equals("non"))
					{
						System.out.println("------------------------------------------------------------------------");
						System.out.println("D�placement termin�");
						
					}
					else if(rep2.equals("Oui") || rep2.equals("oui"))
					{
					}
					else
					{
						while(!rep2.equals("Non") && !rep2.equals("non") && !rep2.equals("Oui") && !rep2.equals("oui"))
						{
						System.out.println("Veuillez bien orthographier Oui ou Non !");
						rep2 = sc.next();
						}
						if(rep2.equals("Non") || rep2.equals("non"))
						{
							System.out.println("------------------------------------------------------------------------");
							System.out.println("D�placement termin�");
						}
					}
				
					
					
				}catch(FileNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				catch(IOException e)
				{
					System.out.println(e.getMessage());
				}
				}
				break;
				
			default :
				System.out.println("Il n'y a pas le nombre "+choix+" comme choix");
				
			}//Fin du switch
		
			
		}
		}
		System.out.println("------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------");
		System.out.println("Fin du programme");
		}
			

	
		}
		


