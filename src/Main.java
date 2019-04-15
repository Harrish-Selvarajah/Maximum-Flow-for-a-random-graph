//Harrish Selvarajah
//code refernece from sanfoundry and geeekforgeeks which helped me out with maximum flow algorithm

public class Main {

    public static void main(String[] args) {

        int numberOfNodes;
        int source;
        int sink;
        int[][] graph;
        int maximumFlow;


        numberOfNodes = getRandomIntegerBetweenRange(6,12);
        graph = new int[numberOfNodes + 1][numberOfNodes + 1];
        System.out.println("Number of Nodes: " + numberOfNodes);


            //iterating through the source vertex
        for (int sourceVertex = 1; sourceVertex <= numberOfNodes; sourceVertex++) {
                //iterating through the sinkvertex
                for (int sinkvertex = 1; sinkvertex <= numberOfNodes; sinkvertex++) {
                    //first if to check of source and sink are directly connected
                    if ((sourceVertex == 1 && sinkvertex == numberOfNodes) || (sourceVertex == numberOfNodes && sinkvertex == 1)) {
                        graph[sourceVertex][sinkvertex] = 0;

                    } else {
                        //checking if source and sink are the same
                        if (sourceVertex == sinkvertex) {
                            graph[sourceVertex][sinkvertex] = 0;
                            //makes sure if nodes dont connect to node1 (connection cant goto source)
                        } else if (sinkvertex == 1) {
                            graph[sourceVertex][sinkvertex] = 0;
                            //making sure source doesnt connect with other nodes(from node connection)(connections cant come from sink)
                        } else if (sourceVertex == numberOfNodes) {
                            graph[sourceVertex][sinkvertex] = 0;
                            //making sure that nodes dont connect back again (2---->3 possible) (3<-----2 not possible)
                        } else if (sinkvertex < sourceVertex) {
                            graph[sourceVertex][sinkvertex] = 0;
                        } else {
                            //capacity is added
                            graph[sourceVertex][sinkvertex] = getRandomIntegerBetweenRange(5,20);
                        }
                    }

                }
            }


            System.out.println("The graph is: ");
            for (int i = 1; i <= numberOfNodes; i++) {         //this equals to the row in our matrix.
                for (int j = 1; j <= numberOfNodes; j++) {   //this equals to the column in each row.
                    System.out.print(" " + graph[i][j] + " ");
                }
                        System.out.println(" "); //change line on console as row comes to end in the matrix.
            }


            source = 1;
            System.out.println("The Source is: "+source);

            sink = numberOfNodes;
            System.out.println("The Sink is: "+sink);

            MaxFlow obj = new MaxFlow(numberOfNodes);

            maximumFlow = obj.networkFlow(graph, source, sink);
            System.out.println("The Maximum Flow for the graph showed above is: "+maximumFlow);


        }

        public static int getRandomIntegerBetweenRange(int min, int max) {

        int x = (int) (Math.random() * ((max - min) + 1)) + min;

        return x;

   }
}