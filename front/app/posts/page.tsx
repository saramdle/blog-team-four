import { Metadata } from "next";
import Container from "../components/Container";
import PostPreview from "../components/PostPreview";
import AddFloating from "./AddFloating";

export const metadata: Metadata = {
  title: "POSTS",
};

export default function Posts() {
  return (
    <Container>
      {/* @ts-expect-error Server Component */}
      <PostPreview />
      <AddFloating />
    </Container>
  );
}
