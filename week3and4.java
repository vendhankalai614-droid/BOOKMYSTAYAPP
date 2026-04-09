import java.util.*;

/* =========================
   DATA MODELS
   ========================= */

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

class Client {
    String name;
    int riskScore;
    double balance;

    Client(String name, int riskScore, double balance) {
        this.name = name;
        this.riskScore = riskScore;
        this.balance = balance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

/* =========================
   MAIN CLASS
   ========================= */

public class AllSortingProblems {

    public static void main(String[] args) {

        System.out.println("===== PROBLEM 1: TRANSACTION SORT =====");

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        bubbleSortTransactions(transactions);
        System.out.println("Bubble Sort (fee): " + transactions);

        insertionSortTransactions(transactions);
        System.out.println("Insertion Sort (fee+ts): " + transactions);

        printHighFee(transactions);


        System.out.println("\n===== PROBLEM 2: CLIENT RISK =====");

        Client[] clients = {
                new Client("C", 80, 1000),
                new Client("A", 20, 500),
                new Client("B", 50, 800)
        };

        bubbleSortClients(clients);
        System.out.println("Bubble (asc): " + Arrays.toString(clients));

        insertionSortClientsDesc(clients);
        System.out.println("Insertion (desc): " + Arrays.toString(clients));

        printTopRisk(clients, 3);


        System.out.println("\n===== PROBLEM 3: TRADE VOLUME =====");

        Trade[] trades = {
                new Trade("t3", 500),
                new Trade("t1", 100),
                new Trade("t2", 300)
        };

        mergeSortTrades(trades, 0, trades.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(trades));

        quickSortTradesDesc(trades, 0, trades.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(trades));

        System.out.println("Total Volume: " + totalVolume(trades));


        System.out.println("\n===== PROBLEM 4: PORTFOLIO =====");

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        mergeSortAssets(assets, 0, assets.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(assets));

        quickSortAssetsDesc(assets, 0, assets.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(assets));
    }

    /* =========================
       PROBLEM 1 METHODS
       ========================= */

    static void bubbleSortTransactions(ArrayList<Transaction> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break; // early termination
        }
    }

    static void insertionSortTransactions(ArrayList<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                     (list.get(j).fee == key.fee &&
                      list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    static void printHighFee(ArrayList<Transaction> list) {
        System.out.print("High-fee outliers: ");
        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.print(t + " ");
                found = true;
            }
        }
        if (!found) System.out.print("none");
        System.out.println();
    }

    /* =========================
       PROBLEM 2 METHODS
       ========================= */

    static void bubbleSortClients(Client[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void insertionSortClientsDesc(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                     (arr[j].riskScore == key.riskScore &&
                      arr[j].balance < key.balance))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void printTopRisk(Client[] arr, int k) {
        System.out.print("Top risks: ");
        for (int i = 0; i < k && i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /* =========================
       PROBLEM 3 METHODS
       ========================= */

    static void mergeSortTrades(Trade[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSortTrades(arr, l, m);
            mergeSortTrades(arr, m + 1, r);
            mergeTrades(arr, l, m, r);
        }
    }

    static void mergeTrades(Trade[] arr, int l, int m, int r) {
        Trade[] temp = new Trade[r - l + 1];
        int i = l, j = m + 1, k = 0;

        while (i <= m && j <= r) {
            if (arr[i].volume <= arr[j].volume)
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, l, temp.length);
    }

    static void quickSortTradesDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionTrades(arr, low, high);
            quickSortTradesDesc(arr, low, pi - 1);
            quickSortTradesDesc(arr, pi + 1, high);
        }
    }

    static int partitionTrades(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) sum += t.volume;
        return sum;
    }

    /* =========================
       PROBLEM 4 METHODS
       ========================= */

    static void mergeSortAssets(Asset[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSortAssets(arr, l, m);
            mergeSortAssets(arr, m + 1, r);
            mergeAssets(arr, l, m, r);
        }
    }

    static void mergeAssets(Asset[] arr, int l, int m, int r) {
        Asset[] temp = new Asset[r - l + 1];
        int i = l, j = m + 1, k = 0;

        while (i <= m && j <= r) {
            if (arr[i].returnRate <= arr[j].returnRate)
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, l, temp.length);
    }

    static void quickSortAssetsDesc(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionAssets(arr, low, high);
            quickSortAssetsDesc(arr, low, pi - 1);
            quickSortAssetsDesc(arr, pi + 1, high);
        }
    }

    static int partitionAssets(Asset[] arr, int low, int high) {
        double pivot = arr[high].returnRate;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
