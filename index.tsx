import React, { useEffect, useState } from "react";
import scss from "./dailyshiftdetails.module.scss";
import {
  Button,
  FormControl,
  FormErrorMessage,
  FormLabel,
  Input,
  Text,
  Select,
  Textarea,
  Table,
  Thead,
  IconButton,
  Tbody,
  Td,
  Th,
  Tr,
  Card,
} from "@chakra-ui/react";
import { SubmitHandler, useFieldArray, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { IoMdAdd } from "react-icons/io";
import { clsx } from "@utils/string";
import { MdDeleteOutline } from "react-icons/md";
import { fetcher } from "@services/fetcher";
import { useNavigationStore } from "@stores/navigation-store";
import Productive from "../ProductiveAndNon-Productive/productive";
import NonProductive from "../ProductiveAndNon-Productive/nonProductive";

const DailyShiftDetails = () => {
  const dailyShiftSchema = z.object({
    typeOfShift: z.string().min(1, { message: "Required" }),
    date: z.string().min(1, { message: "Required" }),
    typeOfDrill: z.string().min(1, { message: "Required" }),
    shiftDetails: z.string().min(1, { message: "Required" }),
  });

  type dailyShiftSchema = z.infer<typeof dailyShiftSchema>;
  const {
    register,
    handleSubmit,
    reset,
    setValue,
    watch,
    control,
    formState: { errors },
  } = useForm<dailyShiftSchema>({
    resolver: zodResolver(dailyShiftSchema),
  });

  const formId = useNavigationStore.use.formId();
  const [productive, setProductive] = useState("");

  // const onSubmit = async (data: dailyShiftSchema) => {
  //   console.log("data", data);
  //   const { addFormation, downReason, ...rest } = data;

  //   const json = {
  //     dailyProId,
  //     // drillData: { ...rest },
  //     addFormation,
  //     downReason,
  //   };
  //   console.log("json daily Shift Detail",json)
  //   const res = await fetcher(
  //     { path: "/drill/save-drill_daily_progress_details" },
  //     {
  //       json,
  //     }
  //   );
  //   if (res.message="success") {
  //     alert(" Data Updated successfully.");
  //     reset();
  //   }
  // };
  const onSubmit: SubmitHandler<dailyShiftSchema> = async (data) => {
    console.log(data, "data");

    const payload = {
      ...data,
      id: formId,
    };

    try {
      const res = await fetcher(
        { path: "/drill/save-daily-shift-details" },
        {
          json: payload,
        }
      );
      console.log("Response from server:", res);
      if (res === "success") {
        alert("Data saved successfully!");
        reset();
      } else {
        alert("Error saving data. Please try again.");
      }
    } catch (error) {
      console.error("Error saving data:", error);
      alert("An error occurred while saving data. Please try again.");
    }
  };
  console.log("productive", productive);

  const handleShiftChange = (event: any) => {
    const value = event.target.value;
    if (value === "productive") {
      setProductive("productive");
    } else {
      setProductive("non-productive");
    } // Set productive state based on selected value
  };
  console.log(errors);

  return (
    <>
      <form noValidate className="mx-auto" onSubmit={handleSubmit(onSubmit)}>
        {/* <div className={scss.progress}> */}
        <div className={scss.header}>
          <Text as="h5" color="orange.500">
            Daily Shift Details
          </Text>
        </div>

        <div className={scss.progress}>
          <div className="row">
            <div className="col-md-4">
              <FormControl isInvalid={!!errors.typeOfShift}>
                <FormLabel>Type of Shift</FormLabel>
                <Select
                  placeholder="Select option"
                  {...register("typeOfShift")}
                  onChange={handleShiftChange}>
                  <option value="productive">Productive</option>
                  <option value="non-productive">Non Productive</option>
                </Select>
                <FormErrorMessage>{errors.typeOfShift?.message}</FormErrorMessage>
              </FormControl>
            </div>

            <div className="col-md-4 ">
              <FormControl isInvalid={!!errors.typeOfDrill}>
                <FormLabel>Type of Drilling</FormLabel>
                <Select placeholder="Select option" {...register("typeOfDrill")}>
                  <option value="augure">Augure</option>
                  <option value="dry">Dry</option>
                  <option value="geo tech">Geo Tech</option>
                  <option value="wet">Wet</option>
                </Select>
                <FormErrorMessage>{errors.typeOfDrill?.message}</FormErrorMessage>
              </FormControl>
            </div>
          </div>
          <div className="mt-4 ">
            <div className="row">
              <div className="col-md-4">
                <FormControl isInvalid={!!errors.date}>
                  <FormLabel>Date</FormLabel>
                  <Input placeholder="select date" type="date" {...register("date")} />
                  <FormErrorMessage>{errors.date?.message}</FormErrorMessage>
                </FormControl>
              </div>

              <div className="col-md-4">
                <FormControl isRequired isInvalid={!!errors.shiftDetails}>
                  <FormLabel>Shift Details</FormLabel>
                  <Select placeholder="Select option" {...register("shiftDetails")}>
                  <option value="general">General</option>
                    <option value="extended">Extended</option>
                    <option value="first">First</option>
                    <option value="second">Second</option>
                    <option value="third">Third</option>
                  </Select>
                  <FormErrorMessage>{errors.shiftDetails?.message}</FormErrorMessage>
                </FormControl>
              </div>
            </div>
          </div>
          <div className="md-6 mt-5">
            {productive === "productive" ? (
              <>
                <Card>
                  <Productive />
                </Card>
              </>
            ) : (
              <Card>
                <NonProductive productive={productive} />
              </Card>
            )}
          </div>
          <div className="flex gap-3 flex-row-reverse mt-4 mb-2">
            <Button colorScheme="facebook">Close</Button>
            <Button colorScheme="facebook">Add New Record</Button>
            <Button colorScheme="facebook">Add New Shift</Button>
            <Button type="submit" colorScheme="facebook">
              Submit
            </Button>
          </div>
        </div>
      </form>
    </>
  );
};

export default DailyShiftDetails;
