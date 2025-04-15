
import React from 'react';
import {
  Document,
  Page,
  Text,
  View,
  StyleSheet
} from '@react-pdf/renderer';

const styles = StyleSheet.create({
  page: {
    padding: 15,
    fontSize: 8,
    fontFamily: 'Helvetica',
  },
  table: {
    display: 'table',
    width: 'auto',
    borderStyle: 'solid',
    borderWidth: 1,
    borderRightWidth: 0,
    borderBottomWidth: 0,
    marginTop: 10,
  },
  tableRow: {
    flexDirection: 'row',
  },
  tableCell: {
    flex: 1,
    borderRightWidth: 1,
    borderBottomWidth: 1,
    padding: 3,
    textAlign: 'center',
  },
  tableSubCell: {
    flex: 1,
    borderRightWidth: 1,
    borderBottomWidth: 1,
    padding: 3,
    textAlign: 'center',
  },
});

const DrillUnitMonthlyProgressPdf = () => (
  <Document>
    <Page size="A4" style={styles.page}>
      {/* Final Table with Nested Sub-columns for Drilling Progress */}
      <View style={styles.table}>
        {/* Header Row */}
        <View style={styles.tableRow}>
          <Text style={styles.tableCell}>Serial No.</Text>
          <Text style={styles.tableCell}>Bore Hole No.</Text>

          <View style={[styles.tableCell, { flex: 2 }]}>
            <Text style={{ textAlign: 'center' }}>Drilling Progress</Text>
            <View style={styles.tableRow}>
              <Text style={styles.tableSubCell}>From (meter)</Text>
              <Text style={styles.tableSubCell}>To (meter)</Text>
            </View>
          </View>

          <Text style={styles.tableCell}>Total Drilling</Text>
          <Text style={styles.tableCell}>Core Recovery</Text>
          <Text style={styles.tableCell}>% Core Recovery</Text>
        </View>

        {/* Data Row Example */}
        <View style={styles.tableRow}>
          <Text style={styles.tableCell}>1</Text>
          <Text style={styles.tableCell}>BH-01</Text>
          <View style={[styles.tableCell, { flex: 2, flexDirection: 'row' }]}>
            <Text style={styles.tableSubCell}>120</Text>
            <Text style={styles.tableSubCell}>135</Text>
          </View>
          <Text style={styles.tableCell}>15</Text>
          <Text style={styles.tableCell}>14.5</Text>
          <Text style={styles.tableCell}>96.6%</Text>
        </View>
      </View>

      {/* Footer Note */}
      <Text style={{ fontSize: 6, marginTop: 8 }}>
        ** This is a computer-generated document. No signature is required. Print only if necessary. **
      </Text>
    </Page>
  </Document>
);

export default DrillUnitMonthlyProgressPdf;
