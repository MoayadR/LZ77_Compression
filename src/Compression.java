import java.util.Scanner;

public class Compression {

    public static boolean isMatch(String Text, int j, int i)
    {
        for (int k = j; k < i; k++)
        {
            if (Text.charAt(k) == Text.charAt(i))
            {
                return true;
            }
        }
        return false;
    }

    public static String Compress(int windowSize, int aheadBuffer)
    {
        //string Text = "ABAABABAABBBBBBBBBBBBA";
        String Text;

        Scanner scanner = new Scanner(System.in);
        Text = scanner.nextLine();
        String output = "";



        int Position = 0;
        int Length = 0;
        char Symbol = Text.charAt(0);




        // first Index in the Text
        output += (char)(Position + '0');
        output += ' ';
        output += (char)(Length + '0');
        output += ' ';
        output += Symbol ;
        output += ' ';

        for (int i = 1; i < Text.length(); i++) // the start of the look ahead buffer
        {
            // i -windowSize = start of windowSearch
            int j = i - windowSize;
            if (j < 0)
            {
                j = 0;
            }
            int maxPosition = 0;
            int maxLenght = 0;
            char symbolMax = ' ';

            if (isMatch(Text, j, i))
            {

                Symbol = Text.charAt(i+1);

                for(; j < i ; j++)
                {
                    Length = 1;
                    if (Text.charAt(i) == Text.charAt(j))
                    {
                        Position = i - j;
                        int k = j + 1;
                        int h = i + 1;



                        while (k < i && (h - i) < aheadBuffer && h < Text.length())
                        {
                            if (Text.charAt(k) == Text.charAt(h))
                            {
                                Length++;
                                k++;
                                h++;

                                // out of bound restriction
                                if (h >= Text.length())
                                {
                                    // null
                                    Symbol = 0;
                                }
                                else
                                {
                                    Symbol = Text.charAt(h);
                                }


                            }
                            else
                            {
                                break;
                            }
                        }
                        // longest match
                        if (Length >= maxLenght)
                        {
                            maxLenght = Length;
                            maxPosition = Position;
                            symbolMax = Symbol;
                        }
                    }

                }
                // shifting i to the new value

                i = i + (maxLenght);
                if(symbolMax == 0){
                    symbolMax ='-';
                }

                output += (char)(maxPosition + '0');
                output += ' ';
                output += (char)(maxLenght + '0');
                output += ' ';
                output += symbolMax ;
                output += ' ';
            }
            else
            {
                Position = 0;
                Symbol = Text.charAt(i);
                Length = 0;

                output += (char)(Position + '0');
                output += ' ';
                output += (char)(Length + '0');
                output += ' ';
                output += Symbol;
                output += ' ';
            }
        }

        return output;
    }

    public static void main(String[] args) {
        String output = Compress(5,5);
        System.out.println(output);
    }
}