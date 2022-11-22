import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class pass1 {

    static int lc=0;
    static LinkedHashMap<String, TableRow> SYMTAB;
    static ArrayList<TableRow> LITTAB;
    static int symIndex=0;

    public static void main(String[] args) throws IOException {
        SYMTAB =new LinkedHashMap<>();
        LITTAB=new ArrayList<>();
        INSTtable lookup=new INSTtable();
        String inpline;
        String code;

        BufferedReader FRead = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter FWrite = new BufferedWriter(new FileWriter("IC.txt"));

        while((inpline = FRead.readLine()) !=null){

                String parts[] = inpline.split("\\s+");

                if(parts[1].equals("START")){
                    lc = Integer.parseInt(parts[2]);
                    code = "(AD,01) (C,"+lc+")";
                    FWrite.write(code+"\n");
                }

                if(parts[1].equals("END")){
                    code = "(AD,02)";
                    FWrite.write(code+"\n");
                }

            if(!parts[0].isEmpty()) //processing of label
            {
                if(SYMTAB.containsKey(parts[0]))
                    SYMTAB.put(parts[0], new TableRow(parts[0], lc, SYMTAB.get(parts[0]).getIndex()));
                else
                    SYMTAB.put(parts[0],new TableRow(parts[0], lc, ++symIndex));
            }
            
            if(parts[1].equals("DC"))
            {
                lc++;
                int constant=Integer.parseInt(parts[2].replace("'",""));
                code="(DL,01)\t(C,"+constant+")";
                FWrite.write(code+"\n");
            }
            else if(parts[1].equals("DS"))
            {

                int size=Integer.parseInt(parts[2].replace("'", ""));
                lc = lc +size;
                code="(DL,02)\t(C,"+size+")";
                FWrite.write(code+"\n");
            }


            if(lookup.getType(parts[1]).equals("IS"))
            {
                code="(IS,0"+lookup.getCode(parts[1])+")\t";
                int j=2;
                String code2="";
                while(j<parts.length)
                {
                    if(lookup.getType(parts[j]).equals("RG"))
                    {
                        code2+=lookup.getCode(parts[j])+" ";
                    }
                    else
                    {
                        if(SYMTAB.containsKey(parts[j]))
                        {
                            int ind=SYMTAB.get(parts[j]).getIndex();
                            code2+= "(S,0"+ind+")";
                        }
                        else
                        {
                            SYMTAB.put(parts[j], new TableRow(parts[j],-1,++symIndex));
                            int ind=SYMTAB.get(parts[j]).getIndex();
                            code2+= "(S,0"+ind+")";
                        }
                    }
                    j++;
                }
                lc++;
                code=code+code2;
                FWrite.write(code+"\n");
            }

        }
        FWrite.close();
        FRead.close();
        createSYMTAB();



        System.out.println("IC : ");
        Scanner sc = new Scanner(new FileReader("IC.txt"));
        while (sc.hasNext()){
            System.out.println(sc.nextLine());
        }
    }
    static void createSYMTAB() throws IOException
    {
        BufferedWriter bw=new BufferedWriter(new FileWriter("SYMTAB.txt"));
        java.util.Iterator<String> iterator = SYMTAB.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            TableRow value = SYMTAB.get(key);
            bw.write(value.getIndex()+"\t" + value.getSymbol()+"\t"+value.getAddress()+"\n");
        }
        bw.close();
    }
}
