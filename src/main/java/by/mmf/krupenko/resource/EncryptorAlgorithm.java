package by.mmf.krupenko.resource;

public enum EncryptorAlgorithm {
    PASSWORD("SHA-1"),
    ID("MD5");

    public final String algorithm;

    EncryptorAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
