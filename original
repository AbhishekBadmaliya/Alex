// SamplePdf.tsx
import React from "react";
import { Page, Text, View, Document, StyleSheet, Image } from "@react-pdf/renderer";

const styles = StyleSheet.create({
  page: {
    padding: 20,
    fontSize: 8,
    fontFamily: "Helvetica",
  },
  section: {
    marginBottom: 5,
  },
  header: {
    textAlign: "center",
    fontSize: 10,
    fontWeight: "bold",
    marginBottom: 10,
  },
  table: {
    display: "table",
    width: "auto",
    borderStyle: "solid",
    borderWidth: 1,
    borderRightWidth: 0,
    borderBottomWidth: 0,
  },
  row: {
    flexDirection: "row",
  },
  cell: {
    borderStyle: "solid",
    borderBottomWidth: 1,
    borderRightWidth: 1,
    padding: 2,
    textAlign: "center",
    flex: 1,
  },
  bold: {
    fontWeight: "bold",
  },
});

const DrillUnitMonthlyProgressPdf = () => (
  <Document>
    <Page size="A4" style={styles.page}>
      <View style={{ marginRight: "8px" }}>
        <Image
          src="../assets/logos/gsi.png"
          style={{
            width: "100px",
            height: "100px",
            borderRadius: "50%",
            objectFit: "contain",
            objectPosition: "center",
          }}
        />
      </View>
      <View style={styles.header}>
        <Text>GOVERNMENT OF INDIA</Text>
        <Text>GEOLOGICAL SURVEY OF INDIA</Text>
        <Text>ENGINEERING DIVISION</Text>
        <Text style={{ marginTop: 5 }}>Drill Unit Monthly Progress Report</Text>
      </View>

      <View style={styles.section}>
        <Text>FSP Code: M2AFGBM-MEP/NC/ER/SU-ODS/2018/1273</Text>
        <Text>Region/Others: EASTERN REGION</Text>
        <Text>Progress For The Month Of: JUL</Text>
      </View>

      {/* First Table Header */}
      <View style={styles.table}>
        <View style={styles.row}>
          {[
            "Unit No.",
            "Area Of Operation",
            "Mineral",
            "Date of commencement of Drilling",
            "Annual Target",
            "Total Drilling Progress till last month",
            "Drilling Progress during the Month",
            "Total Drilling Progress since",
            "Period of Stoppage of works",
          ].map((heading, index) => (
            <Text key={index} style={styles.cell}>
              {heading}
            </Text>
          ))}
        </View>

        {/* Data Row */}
        <View style={styles.row}>
          {Array(9)
            .fill(0)
            .map((_, index) => (
              <Text key={index} style={styles.cell}>
                0
              </Text>
            ))}
        </View>
      </View>

      {/* Second Table Header */}
      <View style={[styles.table, { marginTop: 8 }]}>
        <View style={styles.row}>
          {[
            "Total Nos. of working day",
            "Total nos of shifts during the month",
            "Total No. of drilling shift",
            "Loss of shifts in Area reaming/fishing shift",
            "Loss of shift in Area shifting",
            "Loss of Shifts in Bore Hole shifting",
            "Loss of Productive shift due to breakdown",
            "Loss of shift due to non availability",
            "Loss of shift due to GP Logging/Deviation Test",
            "Loss of shift due to force majeure",
            "Loss of shift due to forest clearance",
            "Loss of shift due to CBM",
            "MISC",
            "Remarks",
          ].map((heading, index) => (
            <Text key={index} style={styles.cell}>
              {heading}
            </Text>
          ))}
        </View>
        <View style={styles.row}>
          {Array(14)
            .fill(0)
            .map((_, index) => (
              <Text key={index} style={styles.cell}>
                0
              </Text>
            ))}
        </View>
      </View>

      {/* Third Table */}
      <View style={[styles.table, { marginTop: 8 }]}>
        <View style={styles.row}>
          {[
            "Serial No.",
            "Bore Hole No.",
            "Drilling Progress From (meter)",
            "To (meter)",
            "Total Drilling",
            "Core Recovery",
            "% Core Recovery",
          ].map((heading, index) => (
            <Text key={index} style={styles.cell}>
              {heading}
            </Text>
          ))}
        </View>
      </View>

      <Text style={{ marginTop: 10 }}>
        ** This is a computer-generated document. No signature is required. Print only
        if necessary. **
      </Text>
    </Page>
  </Document>
);

export default DrillUnitMonthlyProgressPdf;
