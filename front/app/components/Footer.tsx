"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import Container from "./Container";

export default function Footer() {
  const path = usePathname();
  if (path === "/write") {
    return <></>;
  }
  return (
    <footer className='bg-slate-900 text-white'>
      <Container>
        <Link
          href='/'
          className='m-1 block w-60 border-4 border-white p-2 text-center text-sm font-bold uppercase text-white transition-colors duration-300 ease-in-out'
        >
          flavour 4 Favor
        </Link>
      </Container>
    </footer>
  );
}
