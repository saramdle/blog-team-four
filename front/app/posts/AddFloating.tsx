"use client";

import Link from "next/link";
import { IoMdAdd } from "react-icons/io";

export default function AddFloating() {
  return (
    <Link href='/write'>
      <div className='fixed right-20 bottom-20 flex h-14 w-14 cursor-pointer items-center justify-center rounded-full bg-primary/80 text-5xl font-bold text-white shadow-2xl hover:opacity-75'>
        <IoMdAdd />
      </div>
    </Link>
  );
}
