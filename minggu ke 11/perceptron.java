public class perceptron {
    private double[] weights;  // w1 (bias), w2, w3
    private double learningRate;  // μ
    private double[] lastY;  // untuk vektor satuan
    
    public perceptron(double learningRate) {
        this.learningRate = learningRate;
        this.weights = new double[3];  // [bias_weight, w1, w2]
       
        
        this.weights[0] = -0.3;  // bias weight
        this.weights[1] = 0.5;   // weight untuk x1
        this.weights[2] = -0.4;  // weight untuk x2
    }
    
    // Fungsi aktivasi Step (threshold 0)
    private int activation(double sum) {
        return sum >= 0 ? 1 : 0;
    }
    
    // Forward pass dengan input {1, x1, x2}
    public int predict(int bias, int x1, int x2) {
        double sum = weights[0] * bias + weights[1] * x1 + weights[2] * x2;
        return activation(sum);
    }
    
    // Hitung vektor satuan 
    private double unitVectorNorm(double[] vector) {
        double sumSq = 0;
        for (double v : vector) {
            sumSq += v * v;
        }
        return Math.sqrt(sumSq);
    }
    
    private double[] toUnitVector(double[] vector) {
        double norm = unitVectorNorm(vector);
        double[] unit = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            unit[i] = vector[i] / norm;
        }
        return unit;
    }
    
    private double dotProduct(double[] a, double[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }
    
    // Kriteria berhenti
    private boolean shouldStop(double[] oldWeights, double[] newWeights) {
        double[] yHat = toUnitVector(oldWeights);
        double[] wHat = toUnitVector(newWeights);
        double similarity = dotProduct(yHat, wHat);
        return similarity >= 0.999;  // mendekati 1
    }
    
    public void train(int[][] inputs, int[] targets) {
        train(inputs, targets, 100);
    }
    
    public void train(int[][] inputs, int[] targets, int maxEpochs) {
        boolean converged = false;
        int epoch = 0;
        
        System.out.println("Training :\n");
        
        while (!converged && epoch < maxEpochs) {
            converged = true;
            System.out.println("Epoch " + (epoch + 1) + ":");
            System.out.printf("Weights: [%.2f, %.2f, %.2f]\n", weights[0], weights[1], weights[2]);
            
            double[] oldWeights = weights.clone();
            
            for (int i = 0; i < inputs.length; i++) {
                int bias = 1;
                int x1 = inputs[i][0];
                int x2 = inputs[i][1];
                int target = targets[i];
                
                int output = predict(bias, x1, x2);
                int error = target - output;
                
                System.out.printf("  Input: [%d, %d], Target: %d, Output: %d, Error: %d\n", 
                    x1, x2, target, output, error);
                
                if (error != 0) {
                    converged = false;
                    // Update rule: Wj = Wj + μ * Ij * Err
                    weights[0] += learningRate * bias * error;   // update bias weight
                    weights[1] += learningRate * x1 * error;     // update w1
                    weights[2] += learningRate * x2 * error;     // update w2
                }
            }
            
            // Cek kriteria berhenti dengan vektor satuan
            if (converged || shouldStop(oldWeights, weights)) {
                converged = true;
                System.out.println("\n✓ Converged! Ŷ x Ŵ ≈ 1");
                break;
            }
            
            System.out.println();
            epoch++;
        }
        
        System.out.println("\nFinal weights: [" + 
            String.format("%.2f, %.2f, %.2f]", weights[0], weights[1], weights[2]));
    }
    
    public static void main(String[] args) {
        
        // Input sequence = {0 0, 0 1, 1 0, 1 1}
        int[][] inputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        
        // AND Gate
        System.out.println("========================================");
        System.out.println("AND GATE - Single Perceptron");
        System.out.println("========================================");
        int[] andTargets = {0, 0, 0, 1};
        perceptron andPerceptron = new perceptron(0.1);
        andPerceptron.train(inputs, andTargets);
        
        // Test AND
        System.out.println("\nTesting AND:");
        for (int[] input : inputs) {
            int out = andPerceptron.predict(1, input[0], input[1]);
            System.out.printf("%d AND %d = %d\n", input[0], input[1], out);
        }
        
        System.out.println("\n\n");
        
        // OR Gate
        System.out.println("========================================");
        System.out.println("OR GATE - Single Perceptron");
        System.out.println("========================================");
        int[] orTargets = {0, 1, 1, 1};
        perceptron orPerceptron = new perceptron(0.1);
        orPerceptron.train(inputs, orTargets);
        
        // Test OR
        System.out.println("\nTesting OR:");
        for (int[] input : inputs) {
            int out = orPerceptron.predict(1, input[0], input[1]);
            System.out.printf("%d OR %d = %d\n", input[0], input[1], out);
        }
        
        System.out.println("\n\n");
        
        // XOR - Demo 
        System.out.println("========================================");
        System.out.println("XOR GATE - Perceptron (Demontrasi Gagal)");
        System.out.println("========================================");
        System.out.println("XOR = (X1 AND NOT X2) OR (X2 AND NOT X1)");
        System.out.println("Tidak bisa dipisahkan dengan garis lurus!\n");
        
        int[] xorTargets = {0, 1, 1, 0};
        perceptron xorPerceptron = new perceptron(0.1);
        xorPerceptron.train(inputs, xorTargets, 50);
        
        System.out.println("\nTesting XOR (akan gagal):");
        for (int[] input : inputs) {
            int out = xorPerceptron.predict(1, input[0], input[1]);
            System.out.printf("%d XOR %d = %d ✗ (seharusnya %d)\n", 
                input[0], input[1], out, xorTargets[getIndex(input)]);
        }
        System.out.println("\nKesimpulan: XOR membutuhkan Multi-Layer Perceptron!");
    }
    
    private static int getIndex(int[] input) {
        if (input[0] == 0 && input[1] == 0) return 0;
        if (input[0] == 0 && input[1] == 1) return 1;
        if (input[0] == 1 && input[1] == 0) return 2;
        return 3;
    }
}
