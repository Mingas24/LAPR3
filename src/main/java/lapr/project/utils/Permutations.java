package lapr.project.utils;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Location.Location;

/**
 *
 * @author Nuno Capela
 */
public class Permutations {

    public static void heapPermutation(Location poiList[], int size, int n, List<LinkedList<Location>> permutationList) {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1) {
            concatenatePermutation(poiList, n, permutationList);
        }

        for (int i = 0; i < size; i++) {
            heapPermutation(poiList, size - 1, n, permutationList);

            // Se o tamanho for ímpar, troca o primeiro e o último elemento
            if (size % 2 == 1) {
                Location temp = poiList[0];
                poiList[0] = poiList[size - 1];
                poiList[size - 1] = temp;
            } // Se o tamanho for par, troca o primeiro e o último elemento
            else {
                Location temp = poiList[i];
                poiList[i] = poiList[size - 1];
                poiList[size - 1] = temp;
            }
        }
    }

    private static void concatenatePermutation(Location poiList[], int n, List<LinkedList<Location>> permutationList) {
        LinkedList<Location> currentPermutation = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            currentPermutation.add(poiList[i]);
        }
        permutationList.add(currentPermutation);
    }

}
