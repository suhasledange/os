import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class pass2 {
   static ArrayList<TableRow> SYMTAB;
    static BufferedReader FRead;
    static BufferedWriter FWrite;

    public static void main(String[] args) throws IOException {
        SYMTAB=new ArrayList<>();
        String l;

        FRead = new BufferedReader(new FileReader("SYMTAB.txt"));

        while((l=FRead.readLine())!=null)
        {
            String parts[]=l.split("\\s+");
            SYMTAB.add(new TableRow(parts[1], Integer.parseInt(parts[2]),Integer.parseInt(parts[0]) ));
        }
        FRead.close();


        FRead=new BufferedReader(new FileReader("IC.txt"));
        FWrite=new BufferedWriter(new FileWriter("Machine.txt"));

        String line,code;

        while((line=FRead.readLine())!=null)
        {
            String parts[]=line.split("\\s+");
            if(parts[0].contains("AD")||parts[0].contains("DL,02"))
            {
                FWrite.write("\n");
                continue;
            }
            else if(parts.length==2)
            {
                if(parts[0].contains("DL"))
                {
                    parts[0]=parts[0].replaceAll("[^0-9]", "");
                    if(Integer.parseInt(parts[0])==1)
                    {
                        int constant=Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
                        code="00\t0\t"+String.format("%03d", constant)+"\n";
                        FWrite.write(code);


                    }
                }
                else if(parts[0].contains("IS"))
                {
                    int opcode=Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
                    if(opcode==10)
                    {
                        if(parts[1].contains("S"))
                        {
                            int symIndex=Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
                            code=String.format("%02d", opcode)+"\t0\t"+String.format("%03d", SYMTAB.get(symIndex-1).getAddress())+"\n";
                            FWrite.write(code);
                        }

                    }
                }
            }
            else if(parts.length==1 && parts[0].contains("IS"))
            {
                int opcode=Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
                code=String.format("%02d", opcode)+"\t0\t"+String.format("%03d", 0)+"\n";
                FWrite.write(code);
            }
            else if(parts[0].contains("IS") && parts.length==3) //All OTHER IS INSTR
            {
                int opcode=	Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));

                int regcode=Integer.parseInt(parts[1]);

                if(parts[2].contains("S"))
                {
                    int symIndex=Integer.parseInt(parts[2].replaceAll("[^0-9]", ""));
                    code=String.format("%02d", opcode)+"\t"+regcode+"\t"+String.format("%03d", SYMTAB.get(symIndex-1).getAddress())+"\n";
                    FWrite.write(code);
                }

            }
        }
        FWrite.close();
        FRead.close();

        System.out.println("Machine code : ");
        Scanner sc = new Scanner(new FileReader("Machine.txt"));
        while (sc.hasNext()){
            System.out.println(sc.nextLine());
        }
    }
}
