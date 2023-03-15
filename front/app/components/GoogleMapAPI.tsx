"use client";

import { useLoadScript, GoogleMap, Marker } from "@react-google-maps/api";
import { useState } from "react";

export default function GoogleMapAPI() {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY as string,
  });
  const long = "37.536373";
  const lat = "126.886415";

  if (!isLoaded) return <>Loading...</>;
  return (
    <iframe
      src={
        "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1113.7427478555676!2d126.88563551140578!3d37.53581878444285!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357c9ebe4624f76b%3A0xece0e5763b622f1e!2z7J2064yA66qp64-Z67OR7JuQ!5e0!3m2!1sko!2skr!4v1678679889601!5m2!1sko!2skr"
      }
      width='600'
      height='450'
      style={{ border: "0" }}
      allowFullScreen
      loading='lazy'
      referrerPolicy='no-referrer-when-downgrade'
    ></iframe>
  );
}
