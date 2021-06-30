#!/bin/sh

keytool -v -importkeystore -noprompt \
-srckeystore "/etc/pki/ca.truststore" \
-srcstoretype "JKS" \
-srcstorepass "changeit" \
-destkeystore "/opt/cacerts" \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_SESSION_DATA_TRUSTSTORE_PASSW" \
-srckeystore "$EXTR_SESSION_DATA_TRUSTSTORE_PATH" \
-srcstoretype "$EXTR_SESSION_DATA_TRUSTSTORE_TYPE" \
-destkeystore /opt/cacerts \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_INTEGRATION_EMAIL_TRUSTSTORE_PASSW" \
-srckeystore "$EXTR_INTEGRATION_EMAIL_TRUSTSTORE_PATH" \
-srcstoretype "$EXTR_INTEGRATION_EMAIL_TRUSTSTORE_TYPE" \
-destkeystore /opt/cacerts \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_PKB_TRUSTSTORE_PASSW" \
-srckeystore "$EXTR_PKB_TRUSTSTORE_PATH" \
-srcstoretype "$EXTR_PKB_TRUSTSTORE_TYPE" \
-destkeystore /opt/cacerts \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_ECM_TRUSTSTORE_PASSW" \
-srckeystore "$EXTR_ECM_TRUSTSTORE_PATH" \
-srcstoretype "$EXTR_ECM_TRUSTSTORE_TYPE" \
-destkeystore /opt/cacerts \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_TESSA_TRUSTSTORE_PASSW" \
-srckeystore "$EXTR_TESSA_TRUSTSTORE_PATH" \
-srcstoretype "$EXTR_TESSA_TRUSTSTORE_TYPE" \
-destkeystore /opt/cacerts \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_KEY_CERT_TRUSTSTORE_PASSW" \
-srckeystore "$EXTR_KEY_CERT_TRUSTSTORE_PATH" \
-srcstoretype "$EXTR_KEY_CERT_TRUSTSTORE_TYPE" \
-destkeystore /opt/cacerts \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_UKS_TRUSTSTORE_PASSW" \
-srckeystore "$EXTR_UKS_TRUSTSTORE_PATH" \
-srcstoretype "$EXTR_UKS_TRUSTSTORE_TYPE" \
-destkeystore /opt/cacerts \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_MDM_TRUSTSTORE_PASSW" \
-srckeystore "$EXTR_MDM_TRUSTSTORE_PATH" \
-srcstoretype "$EXTR_MDM_TRUSTSTORE_TYPE" \
-destkeystore /opt/cacerts \
-deststoretype JKS \
-deststorepass "changeit"

keytool -v -importkeystore -noprompt \
-srcstorepass "$EXTR_MDM_KEYSTORE_PASSW" \
-srckeystore "$EXTR_MDM_KEYSTORE_PATH" \
-srcstoretype "$EXTR_MDM_KEYSTORE_TYPE" \
-destkeystore /opt/keycerts \
-deststoretype PKCS12 \
-deststorepass "changeit" \
-destkeypass "changeit"

echo "======= keytool -list -keystore /opt/cacerts ======"
keytool -list -storepass "changeit" -keystore /opt/cacerts

echo "======= keytool -list -keystore /opt/keycerts ======"
keytool -list -storepass "changeit" -keystore /opt/keycerts

KEYCERT=/opt/keycerts
CMD='java -jar'
CMD="$CMD -Dfile.encoding=UTF-8"
CMD="$CMD -Djavax.net.ssl.trustStore=/opt/cacerts"
CMD="$CMD -Djavax.net.ssl.trustStorePassword="changeit""
CMD="$CMD -Dcom.sun.jndi.ldap.object.disableEndpointIdentification=true"
if [ -f $KEYCERT ]; then
    CMD="$CMD -Djavax.net.ssl.keyStore=/opt/keycerts"
    CMD="$CMD -Djavax.net.ssl.keyStorePassword="changeit""
    CMD="$CMD -Djavax.net.ssl.keyPassword="changeit""
fi

CMD="$CMD /opt/productprofile.jar"
echo "Run $CMD"
$CMD

