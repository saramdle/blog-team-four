import { Metadata } from "next";
import "./globals.css";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

export const metadata: Metadata = {
  title: "4조",
  description: "4조 블로그 프로젝트",
  category: "blog",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang='ko'>
      <body>
        <div className='flex h-screen flex-col'>
          <Navbar />
          <div className='flex-grow'>{children}</div>
          <Footer />
        </div>
      </body>
    </html>
  );
}
