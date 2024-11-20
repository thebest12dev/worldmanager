package com.thebest12lines.worldmanager.util.internal;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignatureVerifier {
    public boolean verify(String base64PublicKey, String signature, String filePath) {
        try {
            // Decode the Base64 encoded public key
            byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);

            // Generate Public Key
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            // Read the file bytes
            FileInputStream fis = new FileInputStream(filePath);
            byte[] fileBytes = fis.readAllBytes();
            fis.close();

            // Create Signature instance and initialize with the public key
            java.security.Signature verifySig = java.security.Signature.getInstance("SHA256withRSA");
            verifySig.initVerify(publicKey);
            verifySig.update(fileBytes);

            // Decode the Base64 encoded signature
            byte[] signatureBytes = Base64.getDecoder().decode(signature);

            // Verify the signature
            return verifySig.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
