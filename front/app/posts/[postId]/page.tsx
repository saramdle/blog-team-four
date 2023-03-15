import Container from "@/app/components/Container";
import Image from "next/image";

async function getPosts() {
  const res = await fetch("http://localhost:8000/posts");
  if (!res.ok) {
    throw new Error("Failed to fetch data");
  }
  return res.json();
}

export default async function Page({
  params,
}: {
  params: {
    postId: string;
  };
}) {
  const postId = params.postId;
  const posts: Post[] = await getPosts();
  const post = posts?.find((post) => post.id === postId);
  return (
    <Container>
      <h1>{post?.postTitle}</h1>
      <Image
        alt={post?.postTitle || "food image"}
        src={post?.image || ""}
        width={300}
        height={300}
      />
      <p>{post?.content}</p>
      <p>{post?.author}</p>
      <p>{post?.createdAt}</p>
    </Container>
  );
}
